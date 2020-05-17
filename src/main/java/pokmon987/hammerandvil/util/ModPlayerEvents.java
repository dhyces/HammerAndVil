package pokmon987.hammerandvil.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pokmon987.hammerandvil.HAVConfig;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.blocks.BlockVil;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.tileentity.TileVil;
import pokmon987.hammerandvil.tileentity.TileVil.LastHitTool;

@EventBusSubscriber(modid = HammerAndVil.MODID)
public class ModPlayerEvents {
	
	@SubscribeEvent
	public static void onBlockClickedEvent(PlayerInteractEvent.LeftClickBlock event) {
		World worldIn = event.getWorld();
		EntityPlayer playerIn = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		
		if (VilRecipes.isTool(event.getItemStack()) && playerIn.isCreative()) {
			if (worldIn.getBlockState(pos).getBlock().getClass() == BlockVil.class) {
				if (worldIn.getTileEntity(pos).getClass() == TileVil.class) {
					TileVil tile = (TileVil)worldIn.getTileEntity(pos);
					if (tile.getAllStacks() != null) {
						if (!worldIn.isRemote) {
							ItemStack hand = playerIn.getHeldItemMainhand();
							HitHandler hits = tile.hits;
							LastHitTool lastTool = tile.getToolHandler();
							hits.setCurrentHits(lastTool.get() == null || EqualCheck.areEqual(hand, lastTool.get()) ? hits.getCurrentHits() : hits.resetHits());
							NonNullList<ItemStack> stacks = tile.getAllStacks();
							if (!VilRecipes.getVilResult(stacks, hand).isEmpty() && !VilRecipes.getToolForRecipe(hand, stacks).isEmpty()) {
								ItemStack requiredTool = VilRecipes.getToolForRecipe(hand, stacks);
								if (hand.getItem() == requiredTool.getItem() && EqualCheck.areEqual(hand, requiredTool)) {
									lastTool.set(hand.copy());
									String recipeName = VilRecipes.getNameForRecipe(stacks, requiredTool);
									Float requiredHits = VilRecipes.getHitsRequiredByName(recipeName);
									//If it's not empty, the hits variable increases by 1.0F
									hits.increaseHitUntilPoint(requiredHits);
									//System.out.println("The result was not empty! The number of hits is at: " + hits.getCurrentHits());
									worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.3F, 1.5F);
									//If the hits is equal to the current stack's requirement, it replaces the item with the result
									if (hits.getCurrentHits() >= requiredHits) {
										if (!HAVConfig.General.dropOnCraft) {
											tile.getInventory().setStackInSlot(0, VilRecipes.getVilResult(stacks, hand));
											tile.getInventory().setStackInSlot(1, ItemStack.EMPTY);
											tile.getInventory().setStackInSlot(2, ItemStack.EMPTY);
										} else {
											tile.setAllStacksEmpty();
											EntityItem entity = new EntityItem(worldIn, pos.getX()+0.5D, pos.getY()+1D, pos.getZ()+0.5D, VilRecipes.getVilResult(stacks, hand));
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
								}
							}
						}
						event.setCanceled(true);
					}
				}
			}
		}
	}
}