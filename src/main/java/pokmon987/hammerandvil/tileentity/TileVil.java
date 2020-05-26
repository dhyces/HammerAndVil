package pokmon987.hammerandvil.tileentity;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import pokmon987.hammerandvil.HAVConfig.General;
import pokmon987.hammerandvil.util.HitHandler;

public class TileVil extends TileEntity {
	
	private VilInventoryHandler inventory = new VilInventoryHandler(3);
	public HitHandler hits = new HitHandler();
	private LastHitTool lastHit = new LastHitTool();
	
	public IItemHandlerModifiable getInventory() {
		return this.inventory;
	}
	
	public void setAllStacksEmpty() {
		getInventory().setStackInSlot(0, ItemStack.EMPTY);
		getInventory().setStackInSlot(1, ItemStack.EMPTY);
		getInventory().setStackInSlot(2, ItemStack.EMPTY);
	}
	
	public NonNullList<ItemStack> getAllStacks() {
		NonNullList<ItemStack> inventory = NonNullList.create();
		if (getInventory().getStackInSlot(0).isEmpty()) {return null;}
		inventory.add(getInventory().getStackInSlot(0));
		if (!getInventory().getStackInSlot(1).isEmpty()) {inventory.add(getInventory().getStackInSlot(1));}
		if (!getInventory().getStackInSlot(2).isEmpty()) {inventory.add(getInventory().getStackInSlot(2));}
		return inventory;
	}
	
	public LastHitTool getToolHandler() {
		return this.lastHit;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
//		if (tag.hasKey("Items") && tag.hasKey("hits") && tag.hasKey("tool")) {
//			this.getInventory().setStackInSlot(0, new ItemStack(tag.getCompoundTag("item")));
//			this.getToolHandler().set(new ItemStack(tag.getCompoundTag("tool")));
//			this.hits.setCurrentHits(tag.getFloat("hits"));
		if (tag.hasKey("hits")) {
			this.hits.setCurrentHits(tag.getInteger("hits"));
			
		}
		if (tag.hasKey("tool")) {
			this.getToolHandler().set(new ItemStack(tag.getCompoundTag("tool")));
		}
		if (tag.hasKey("Items")) {
			NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);
			ItemStackHelper.loadAllItems(tag, stacks);
			this.getInventory().setStackInSlot(0, stacks.get(0));
			this.getInventory().setStackInSlot(1, stacks.get(1));
			this.getInventory().setStackInSlot(2, stacks.get(2));
			return;
		}
		if (tag.hasKey("item") && !tag.hasKey("Items")) {
			this.getInventory().setStackInSlot(0, new ItemStack(tag.getCompoundTag("item")));
			return;
		}
		this.getInventory().setStackInSlot(0, ItemStack.EMPTY);
		this.getInventory().setStackInSlot(1, ItemStack.EMPTY);
		this.getInventory().setStackInSlot(2, ItemStack.EMPTY);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NonNullList<ItemStack> stacks = NonNullList.create();
		stacks.add(this.getInventory().getStackInSlot(0));
		stacks.add(this.getInventory().getStackInSlot(1));
		stacks.add(this.getInventory().getStackInSlot(2));
		ItemStack lastTool = this.getToolHandler().get();
		int hits = this.hits.getCurrentHits();
		if (!stacks.isEmpty()) {
			NBTTagCompound compoundTool = new NBTTagCompound();
			ItemStackHelper.saveAllItems(tag, stacks);
			lastTool.writeToNBT(compoundTool);
			tag.setTag("tool", compoundTool);
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
	
	public class LastHitTool {
		ItemStack tool = ItemStack.EMPTY;
		
		public void set(ItemStack heldTool) {
			this.tool = heldTool;
			TileVil.this.markDirty();
		}
		
		public ItemStack get() {
			return tool;
		}
	}
	
	public class VilInventoryHandler extends ItemStackHandler {
		
		private VilInventoryHandler(int size) {
			 super(size);
		}
		
		@Override
		public int getSlots() {
			return 3;
		}

		@Override
		public int getSlotLimit(int slot) {
			//General.stackSize != 0 ? General.stackSize : 
			return 8;
		}
		
		@Override
		public void onContentsChanged(int slot) {
			TileVil.this.markDirty();
			
		}
	}
}