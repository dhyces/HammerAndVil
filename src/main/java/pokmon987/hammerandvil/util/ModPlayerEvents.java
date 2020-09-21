package pokmon987.hammerandvil.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.blocks.BlockVil;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.tileentity.TileVil;

@EventBusSubscriber(modid = HammerAndVil.MODID)
public class ModPlayerEvents {
	
	@SubscribeEvent
	public static void onBlockClickedEvent(PlayerInteractEvent.LeftClickBlock event) {
		EntityPlayer playerIn = event.getEntityPlayer();
		if (VilRecipes.isTool(event.getItemStack()) && playerIn.isCreative()) {
			BlockPos pos = event.getPos();
			World worldIn = event.getWorld();
			IBlockState blockState = worldIn.getBlockState(pos);
			if (blockState.getBlock().getClass() == BlockVil.class) {
				if (worldIn.getTileEntity(pos).getClass() == TileVil.class) {
					TileVil tile = (TileVil)worldIn.getTileEntity(pos);
					if (tile.getAllStacks() != null) {
						event.setCanceled(true);
						BlockVil block = (BlockVil)blockState.getBlock();
						block.onBlockClicked(worldIn, pos, playerIn);
					}
				}
			}
		}
	}
}