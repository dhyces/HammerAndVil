package pokmon987.hammerandvil.integration.crafttweaker;


import javax.annotation.Nullable;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
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
	public static void addRecipe(String name, IItemStack output, IIngredient input, Float hits, @Nullable IIngredient tool) {
		CraftTweaker.LATE_ACTIONS.add(new Add(name, CraftTweakerMC.getItemStack(output), input, hits, tool));
	}
	
	private static class Add implements IAction {
		private final ResourceLocation name;
		private final ItemStack output;
		private final IIngredient input;
		private final Float hits;
		private final IIngredient tool;
		
		public Add(String name, ItemStack output, IIngredient input, Float hits, IIngredient tool) {
			this.name = new ResourceLocation(HammerAndVil.MODID, name);
			this.output = output;
			this.input = input;
			this.hits  = hits;
			this.tool = tool;
		}
		
		@Override
		public void apply() {
			boolean result = new VilRecipes().getNameOutList().containsKey(name.toString());
			ItemStack toolStack = CraftTweakerMC.getItemStack(tool);
			ItemStack requiredTool = VilRecipes.getToolForRecipe(toolStack, CraftTweakerMC.getItemStack(input));
			if ((result == false || !MetaCheck.hasEqualMeta(toolStack, requiredTool)) && input.getAmount() == 1) {
				if (tool instanceof IOreDictEntry) {
					for (int i = 0; i < tool.getItems().size(); i++) {
						VilRecipes.addVilRecipe(name.toString() + i, output, CraftTweakerMC.getItemStack(input), hits, CraftTweakerMC.getItemStack(tool.getItems().get(i)));
					}
				} else {
					VilRecipes.addVilRecipe(name.toString(), output, CraftTweakerMC.getItemStack(input), hits, toolStack);
				}
			} else {
				CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + input + " " + tool);
			}
		}

		@Override
		public String describe() {
			CraftTweakerAPI.logDefault("input: " + CraftTweakerMC.getItemStack(input) + " output: " + output + " tool: " + tool);
			return "Added vil recipe for " + output.getItem().getRegistryName();	
		}
		
	}
}