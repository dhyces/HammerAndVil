package pokmon987.hammerandvil.integration.hwyla;

import java.util.ArrayList;
import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.SpecialChars;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.util.MetaCheck;

public class HUDHandlerVil implements IWailaDataProvider {
	
	VilRecipes recipes = new VilRecipes();
	
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (!config.getConfig(HammerAndVil.MODID + ".vil")) {return tooltip;}
		
		NBTTagCompound itemTag = accessor.getNBTData().getCompoundTag("item");
		if (new ItemStack(itemTag).isEmpty()) {return tooltip;}
		NBTTagCompound lastTool = accessor.getNBTData().getCompoundTag("tool");
		short hitsTag = (short)accessor.getNBTData().getFloat("hits");
		ItemStack result = VilRecipes.getVilResult(new ItemStack(itemTag), accessor.getPlayer().getHeldItemMainhand());
		String recipeName = VilRecipes.getNameForRecipe(new ItemStack(itemTag), accessor.getPlayer().getHeldItemMainhand());
		float hitsTotalF = VilRecipes.getHitsRequiredByName(recipeName) != null ? (float)VilRecipes.getHitsRequiredByName(recipeName) : 0;
		short hitsTotal = (short)hitsTotalF;
		String renderString = "";
		
		List<ItemStack> inventory = new ArrayList<>();
		
		inventory.add(0, new ItemStack(itemTag));
		inventory.add(1, accessor.getPlayer().getHeldItemMainhand());
		inventory.add(2, result);
		
		if (inventory.get(0) != null) {
			String name = inventory.get(0).getItem().getRegistryName().toString();
			renderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(0).getCount()), String.valueOf(inventory.get(0).getItemDamage()));
		} else {renderString += SpecialChars.getRenderString("waila.stack", "2");}
		if (inventory.get(1) != null && !result.isEmpty()) {
			String name = inventory.get(1).getItem().getRegistryName().toString();
			renderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(1).getCount()), String.valueOf(inventory.get(1).getItemDamage()));
		} else {renderString += SpecialChars.getRenderString("waila.stack", "2");}
		if (hitsTotal > 0) {
			short hitsCurrent = MetaCheck.hasEqualMeta(accessor.getPlayer().getHeldItemMainhand(), new ItemStack(lastTool)) ? hitsTag : 0;
			renderString += SpecialChars.getRenderString("waila.progress", String.valueOf(hitsCurrent), String.valueOf(hitsTotal));
		}
		if (inventory.get(2) != null && !result.isEmpty()) {
			String name = inventory.get(2).getItem().getRegistryName().toString();
			renderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(2).getCount()), String.valueOf(inventory.get(2).getItemDamage()));
		} else {renderString += SpecialChars.getRenderString("waila.stack", "2");}
		
		tooltip.add(renderString);
		
		return tooltip;
	}
	
	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return te.writeToNBT(tag);
	}
}