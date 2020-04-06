package pokmon987.hammerandvil.integration.crafttweaker;


import javax.annotation.Nullable;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.CraftTweaker;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.util.MetaCheck;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods." + HammerAndVil.MODID + ".Vil")
public class CTVilRecipe {
	
	@ZenMethod
	public static void addRecipe(String name, IItemStack output, IIngredient input, Float hits, @Nullable IItemStack tool) {
		CraftTweaker.LATE_ACTIONS.add(new Add(name, CraftTweakerMC.getItemStack(output), input, hits, CraftTweakerMC.getItemStack(tool)));
	}
	
	private static class Add implements IAction {
		private final ResourceLocation name;
		private final ItemStack output;
		private final IIngredient input;
		private final Float hits;
		private final ItemStack tool;
		
		public Add(String name, ItemStack output, IIngredient input, Float hits, ItemStack tool) {
			this.name = new ResourceLocation(HammerAndVil.MODID, name);
			this.output = output;
			this.input = input;
			this.hits  = hits;
			this.tool = tool;
		}
		
		@Override
		public void apply() {
			ItemStack result = VilRecipes.getVilResult(CraftTweakerMC.getItemStack(input), tool);
			ItemStack requiredTool = VilRecipes.getRequiredTool(CraftTweakerMC.getItemStack(input));
			if (result == ItemStack.EMPTY || !MetaCheck.hasEqualMeta(tool, requiredTool)) {
				VilRecipes.addVilRecipe(name.toString(), output, CraftTweakerMC.getItemStack(input), hits, tool);
			} else if (result == ItemStack.EMPTY && tool.isEmpty()) {
				VilRecipes.addVilRecipe(name.toString(), output, CraftTweakerMC.getItemStack(input), hits, tool);
			} else {
				CraftTweakerAPI.logError("The result may not have been empty: " + result.getItem().getRegistryName() + ". Or a recipe with that input and tool already exists: " + input + " " + tool);
			}
		}

		@Override
		public String describe() {
			CraftTweakerAPI.logDefault("input: " + CraftTweakerMC.getItemStack(input) + " output: " + output + " tool: " + tool);
			return "Added vil recipe for " + output.getItem().getRegistryName();	
		}
		
	}
}