package pokmon987.hammerandvil.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import pokmon987.hammerandvil.HAVConfig;
import pokmon987.hammerandvil.recipes.IVilRecipe;
import pokmon987.hammerandvil.recipes.OreVilRecipe;
import pokmon987.hammerandvil.recipes.VilRecipe;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.util.EqualCheck;

public class TileVil extends TileEntity {
	
	private VilInventoryHandler inventory = new VilInventoryHandler(3);
	public int hits = 0;
	
	public VilInventoryHandler getInventory() {
		return this.inventory;
	}
	
	public void updateTileAfterHit(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		ItemStack tool = playerIn.getHeldItemMainhand();
		inventory.setLastTool(tool);
		inventory.checkRecipe();
		if (inventory.currentRecipe != null) {
			hits += 1;
			worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.3F, 1.5F);
			if (hits >= inventory.currentRecipe.getHits()) {
				inventory.replaceWithResult();
				worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.8F, 1.9F);
				if (HAVConfig.General.dropOnCraft) {
					EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D, inventory.getStackInSlot(0));
					item.motionX = 0;
					item.motionY = 0;
					item.motionZ = 0;
					worldIn.spawnEntity(item);
					setAllStacksEmpty();
				}
				damageTool(tool, playerIn);
				hits = 0;
			}
		}
	}
	
	public void damageTool(ItemStack tool, EntityPlayer playerIn) {
		if (tool.isItemStackDamageable()) {
			tool.damageItem(1, playerIn);
		} else if (tool.serializeNBT().hasKey("tag") || tool.getItem().getRegistryName().toString().contentEquals("immersiveengineering:tool")) {
			NBTTagCompound tag = new NBTTagCompound();
			tool.writeToNBT(tag);
			if (tag.getCompoundTag("tag").hasNoTags()) {
				tag.setTag("tag", new NBTTagCompound());
			}
			int damageAmount = tag.getCompoundTag("tag").getInteger("Damage");
			tag.getCompoundTag("tag").setInteger("Damage", damageAmount + 2);
			tool.deserializeNBT(tag);
		} else {
			tool.shrink(1);
		}
	}
	
	public void setAllStacksEmpty() {
		getInventory().setStackInSlot(0, ItemStack.EMPTY);
		getInventory().setStackInSlot(1, ItemStack.EMPTY);
		getInventory().setStackInSlot(2, ItemStack.EMPTY);
	}
	
	public NonNullList<ItemStack> getAllStacks() {
		NonNullList<ItemStack> inventory = NonNullList.create();
		if (getInventory().getStackInSlot(0).isEmpty()) {return inventory;}
		inventory.add(getInventory().getStackInSlot(0));
		if (!getInventory().getStackInSlot(1).isEmpty()) {inventory.add(getInventory().getStackInSlot(1));}
		if (!getInventory().getStackInSlot(2).isEmpty()) {inventory.add(getInventory().getStackInSlot(2));}
		return inventory;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
//		if (tag.hasKey("Items") && tag.hasKey("hits") && tag.hasKey("tool")) {
//			this.getInventory().setStackInSlot(0, new ItemStack(tag.getCompoundTag("item")));
//			this.getToolHandler().set(new ItemStack(tag.getCompoundTag("tool")));
//			this.hits.setCurrentHits(tag.getFloat("hits"));
		if (tag.hasKey("hits")) {
			this.hits = tag.getInteger("hits");
			
		}
		if (tag.hasKey("tool")) {
			this.getInventory().setLastTool(new ItemStack(tag.getCompoundTag("tool")));
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
		ItemStack lastTool = getInventory().lastHitTool;
		int hits = this.hits;
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
	
	
	
	public class VilInventoryHandler extends ItemStackHandler {
		
		public ItemStack lastHitTool = ItemStack.EMPTY;
		public IVilRecipe currentRecipe;
		
		private VilInventoryHandler(int size) {
			 super(size);
		}
		
		/** Sets the lastHitTool to the input tool, if the previous last tool is not equal to the new tool, returns true*/
		public boolean setLastTool(ItemStack newTool) {
			if (EqualCheck.areEqual(lastHitTool, newTool)) {return false;}
			lastHitTool = newTool;
			return true;
		}
		
		public boolean checkRecipe() {
			System.out.println(getAllStacks());
			if (currentRecipe != null && !getAllStacks().isEmpty()) {
				if (currentRecipe instanceof VilRecipe) {
					VilRecipe trueRecipe = (VilRecipe) currentRecipe;
					if (trueRecipe.matches(getAllStacks())) {
						if (trueRecipe.getTool().stream().anyMatch(c -> c.isItemEqualIgnoreDurability(lastHitTool))) {
							return true;
						}
					}
				} else if (currentRecipe instanceof OreVilRecipe) {
					OreVilRecipe trueRecipe = (OreVilRecipe) currentRecipe;
					if (trueRecipe.allMatch(getAllStacks())) {
						if (trueRecipe.getTool().stream().anyMatch(c -> c.isItemEqualIgnoreDurability(lastHitTool))) {
							return true;
						}
					}
				}
			}
			hits = 0;
			setRecipe(VilRecipes.getRecipe(getAllStacks(), lastHitTool));
			return false;
		}
		
		public void setRecipe(IVilRecipe newRecipe) {
			currentRecipe = newRecipe;
		}
		
		public void replaceWithResult() {
			ItemStack output = currentRecipe.getOutput();
			setAllStacksEmpty();
			setStackInSlot(0, output);
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
			checkRecipe();
			TileVil.this.markDirty();
		}
	}
}