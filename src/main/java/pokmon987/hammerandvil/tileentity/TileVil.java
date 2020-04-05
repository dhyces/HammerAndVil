package pokmon987.hammerandvil.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import pokmon987.hammerandvil.util.HitHandler;

public class TileVil extends TileEntity {
	
	private VilInventoryHandler inventory = new VilInventoryHandler(1);
	public HitHandler hits = new HitHandler();
	
	public IItemHandlerModifiable getInventory() {
		return this.inventory;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if (tag.hasKey("item") && tag.hasKey("hits")) {
			this.getInventory().setStackInSlot(0, new ItemStack(tag.getCompoundTag("item")));
			this.hits.setCurrentHits(tag.getFloat("hits"));
		} else {
			this.getInventory().setStackInSlot(0, ItemStack.EMPTY);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		ItemStack stack = this.getInventory().getStackInSlot(0);
		Float hits = this.hits.getCurrentHits();
		if (!stack.isEmpty()) {
			NBTTagCompound compound = new NBTTagCompound();
			stack.writeToNBT(compound);
			tag.setTag("item", compound);
			tag.setFloat("hits", hits);
		}
		return tag;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new SPacketUpdateTileEntity(getPos(), 1, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public final NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	
	
	public class VilInventoryHandler extends ItemStackHandler {
		
		private VilInventoryHandler(int size) {
			 super(size);
		}
		
		@Override
		public int getSlots() {
			return 1;
		}

		@Override
		public int getSlotLimit(int slot) {
			return 1;
		}
		
		@Override
		public void onContentsChanged(int slot) {
			TileVil.this.markDirty();
			
		}
	}
}