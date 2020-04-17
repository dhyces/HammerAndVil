package pokmon987.hammerandvil.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokmon987.hammerandvil.HAVConfig;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.tileentity.TileVil;
import pokmon987.hammerandvil.tileentity.TileVil.LastHitTool;
import pokmon987.hammerandvil.util.HitHandler;
import pokmon987.hammerandvil.util.MetaCheck;

public class BlockVil extends Block {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final AxisAlignedBB X_AXIS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.09D, 1.0D, 0.85D, 0.91D);
	protected static final AxisAlignedBB Z_AXIS_AABB = new AxisAlignedBB(0.09D, 0.0D, 0.0D, 0.91D, 0.85D, 1.0D);
	
	public BlockVil() {
		super(Material.ANVIL);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setHardness(3.0F);
		setRegistryName("vil");
		setUnlocalizedName(HammerAndVil.MODID + ".vil");
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing facing) {
		return BlockFaceShape.UNDEFINED;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, placer.getHorizontalFacing());
		
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
		
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileVil tile = (TileVil)worldIn.getTileEntity(pos);
		ItemStack stack = tile.getInventory().getStackInSlot(0);
		if (!stack.isEmpty()) {
			EntityItem entity = new EntityItem(worldIn, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, stack);
			worldIn.spawnEntity(entity);
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing enumfacing = state.getValue(FACING);
		return enumfacing.getAxis() == EnumFacing.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
		
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			ItemStack handItem = playerIn.getHeldItemOffhand().isEmpty() ? playerIn.getHeldItemMainhand() : playerIn.getHeldItemOffhand();
			TileVil tile = (TileVil)worldIn.getTileEntity(pos);
			if (tile != null) {
				ItemStack stack = tile.getInventory().getStackInSlot(0);
				// if stack is less that the max slot size and hand is equal to the current stack
				if (stack.getCount() < tile.getInventory().getSlotLimit(0) && !handItem.isEmpty() && ((MetaCheck.hasEqualMeta(handItem, stack) && ItemStack.areItemStackTagsEqual(stack, handItem)) || stack.isEmpty()) && stack.getMaxStackSize() > 1) {
					ItemStack itemInv = handItem.copy();
					itemInv.setCount(1);
					// increase count by one
					tile.getInventory().insertItem(0, itemInv, false);
					tile.hits.resetHits();
					handItem.setCount(handItem.getCount()-1);
					worldIn.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5F, 1.0F);
					worldIn.notifyBlockUpdate(pos, state, state, 1);
					// else if player is also holding shift/crouching, remove the whole stack and spawn it in the world
				} else {
					EntityItem entity = new EntityItem(worldIn, pos.getX()+0.5D, pos.getY()+0.9D, pos.getZ()+0.5D, stack.copy());
					entity.motionX = 0;
					entity.motionY = 0;
					entity.motionZ = 0;
					worldIn.spawnEntity(entity);
					tile.getInventory().setStackInSlot(0, ItemStack.EMPTY);
					tile.hits.resetHits();
					worldIn.notifyBlockUpdate(pos, state, state, 1);
				}
				tile.markDirty();
			}
		}
		return true;
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (!worldIn.isRemote) {
			//System.out.println("The block was clicked! ");
			//Establishes the initial variables
			if (this.blockHardness == -1.0F) {this.setHardness(3.0F);}
			ItemStack hand = playerIn.getHeldItemMainhand();
			TileVil tile = (TileVil)worldIn.getTileEntity(pos);
			HitHandler hits = tile.hits;
			LastHitTool lastTool = tile.getToolHandler();
			hits.setCurrentHits(lastTool.get() == null || MetaCheck.hasEqualMeta(hand, lastTool.get()) ? hits.getCurrentHits() : hits.resetHits());
			ItemStack stack = tile.getInventory().getStackInSlot(0);
			//Will check if the held item is a hammer or not
			if (!VilRecipes.getVilResult(stack, hand).isEmpty() && !VilRecipes.getToolForRecipe(hand, stack).isEmpty()) {
				ItemStack requiredTool = VilRecipes.getToolForRecipe(hand, stack);
				/**System.out.println("Hand was holding hammer! The stack is currently: " + stack);
				*System.out.println("Vil result will be: " + VilRecipes.getVilResult(stack));
				*System.out.println("Required hits will be: " + VilRecipes.getHitsRequiredForStack(stack));
				**/
				//Checks to make sure that the vilResult for the inventory slot is not empty
				//System.out.println("RequiredTool is: " + requiredTool.getItem());
				//System.out.println("hasEqualMeta returns: " + MetaCheck.hasEqualMeta(hand, requiredTool));
				
				if (hand.getItem() == requiredTool.getItem() && MetaCheck.hasEqualMeta(hand, requiredTool)) {
					setBlockUnbreakable();
					lastTool.set(hand.copy());
					String recipeName = VilRecipes.getNameForRecipe(stack, requiredTool);
					Float requiredHits = VilRecipes.getHitsRequiredByName(recipeName);
					//If it's not empty, the hits variable increases by 1.0F
					hits.increaseHitUntilPoint(requiredHits);
					//System.out.println("The result was not empty! The number of hits is at: " + hits.getCurrentHits());
					worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.3F, 1.5F);
					//If the hits is equal to the current stack's requirement, it replaces the item with the result
					if (hits.getCurrentHits() >= requiredHits) {
						if (!HAVConfig.General.dropOnCraft) {
							tile.getInventory().setStackInSlot(0, VilRecipes.getVilResult(stack, hand));
						} else {
							tile.getInventory().setStackInSlot(0, ItemStack.EMPTY);
							EntityItem entity = new EntityItem(worldIn, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, VilRecipes.getVilResult(stack, hand));
							entity.motionX = 0;
							entity.motionY = 0;
							entity.motionZ = 0;
							worldIn.spawnEntity(entity);
						}
						worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.8F, 1.9F);
						worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 1);
						hits.resetHits();
						//add config option here to if damage item is onCraft then damage
						if (hand.getMaxDamage() > 0) {
							//if the hand item has a maxdamage that is over 0, damage the item by 1
							hand.damageItem(1, playerIn);
						} else if (hand.serializeNBT().hasKey("tag")) {
							//if the hand item has an NBT key that is "tag", then it will damage the item by two points
							//this is for Immersive Engineering tools, because of their tools being setup with subtypes
							int damageAmount = hand.serializeNBT().getCompoundTag("tag").getInteger("Damage");
							NBTTagCompound tag = new NBTTagCompound();
							hand.writeToNBT(tag);
							tag.getCompoundTag("tag").setInteger("Damage", damageAmount + 2);
							hand.serializeNBT().merge(tag);
						} else {
							// if nothing else is true, then shrink the itemstack size by 1
							hand.shrink(1);
						}
					}
					if (playerIn.isCreative()) {
						//This area was for setting up a way for the block to not be broken
						
					}
				}
			}
		}
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.playEvent(1031, pos, 0);
		super.onBlockAdded(worldIn, pos, state);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new TileVil();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
}