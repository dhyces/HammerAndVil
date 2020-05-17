package pokmon987.hammerandvil.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pokmon987.hammerandvil.HammerAndVil;

public class ItemHammer extends Item {
	
	public ItemHammer() {
		setCreativeTab(CreativeTabs.TOOLS);
		setMaxStackSize(1);
		//add config here to set the item's max damage
		setMaxDamage(1000);
		setRegistryName(new ResourceLocation(HammerAndVil.MODID, "hammer"));
		setUnlocalizedName(HammerAndVil.MODID + ".hammer");
	}
	
	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return super.canDestroyBlockInCreative(world, pos, stack, player);
	}
}