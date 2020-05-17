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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.tileentity.TileVil;
import pokmon987.hammerandvil.util.EqualCheck;

public class HUDHandlerVil implements IWailaDataProvider {
	
	VilRecipes recipes = new VilRecipes();
	
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (!config.getConfig(HammerAndVil.MODID + ".vil")) {return tooltip;}
		
		TileVil tile = (TileVil)accessor.getTileEntity();
		NonNullList<ItemStack> items = tile.getAllStacks();
		float currentItem = accessor.getPlayer().getCooldownPeriod();
		if (items == null) {return tooltip;}
		
		NBTTagCompound lastTool = accessor.getNBTData().getCompoundTag("tool");
		short hitsTag = (short)accessor.getNBTData().getFloat("hits");
		ItemStack result = currentItem != 0.0F ? VilRecipes.getVilResult(items, accessor.getPlayer().getHeldItemMainhand()) : ItemStack.EMPTY;
		String recipeName = VilRecipes.getNameForRecipe(items, accessor.getPlayer().getHeldItemMainhand());
		float hitsTotalF = VilRecipes.getHitsRequiredByName(recipeName) != null ? (float)VilRecipes.getHitsRequiredByName(recipeName) : 0;
		short hitsTotal = (short)hitsTotalF;
		String renderString = "";
		String renderUnderString = "";
		
		List<ItemStack> inventory = new ArrayList<>();
		
		inventory.add(0,items.get(0));
		if (items.size() > 1) {inventory.add(1,items.get(1));} else {inventory.add(1,ItemStack.EMPTY);}
		if (items.size() > 2) {inventory.add(2,items.get(2));} else {inventory.add(2,ItemStack.EMPTY);}
		inventory.add(3,accessor.getPlayer().getHeldItemMainhand());
		inventory.add(4,result);
		
		if (!inventory.get(0).isEmpty()) {
			String name = inventory.get(0).getItem().getRegistryName().toString();
			renderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(0).getCount()), String.valueOf(inventory.get(0).getItemDamage()));
		} else {renderString += SpecialChars.getRenderString("waila.stack", "2");}
		if (!inventory.get(1).isEmpty()) {
			String name = inventory.get(1).getItem().getRegistryName().toString();
			renderUnderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(1).getCount()), String.valueOf(inventory.get(1).getItemDamage()));
		} else {renderUnderString += SpecialChars.getRenderString("waila.stack", "2");}
		if (!inventory.get(2).isEmpty()) {
			String name = inventory.get(2).getItem().getRegistryName().toString();
			renderUnderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(2).getCount()), String.valueOf(inventory.get(2).getItemDamage()));
		} else {renderUnderString += SpecialChars.getRenderString("waila.stack", "2");}
		if (!inventory.get(3).isEmpty() && !result.isEmpty()) {
			String name = inventory.get(3).getItem().getRegistryName().toString();
			renderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(3).getCount()), String.valueOf(inventory.get(3).getItemDamage()));
		} else {renderString += SpecialChars.getRenderString("waila.stack", "2");}
		if (hitsTotal > 0) {
			short hitsCurrent = EqualCheck.areEqual(accessor.getPlayer().getHeldItemMainhand(), new ItemStack(lastTool)) ? hitsTag : 0;
			renderString += SpecialChars.getRenderString("waila.progress", String.valueOf(hitsCurrent), String.valueOf(hitsTotal));
		}
		if (!inventory.get(4).isEmpty() && !result.isEmpty()) {
			String name = inventory.get(4).getItem().getRegistryName().toString();
			renderString += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory.get(4).getCount()), String.valueOf(inventory.get(4).getItemDamage()));
		} else {renderString += SpecialChars.getRenderString("waila.stack", "2");}
		
		tooltip.add(renderString);
		if (!inventory.get(1).isEmpty()) {tooltip.add(renderUnderString);}
		
		return tooltip;
	}
	
	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return te.writeToNBT(tag);
	}
}