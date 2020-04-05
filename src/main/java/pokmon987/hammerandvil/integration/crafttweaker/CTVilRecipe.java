package pokmon987.hammerandvil.integration.crafttweaker;

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
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods." + HammerAndVil.MODID + ".Vil")
public class CTVilRecipe {
	
	@ZenMethod
	public static void addRecipe(String name, IItemStack output, IIngredient input, Float hits) {
		CraftTweaker.LATE_ACTIONS.add(new Add(name, CraftTweakerMC.getItemStack(output), input, hits));
	}
	
	private static class Add implements IAction {
		private final ResourceLocation name;
		private final ItemStack output;
		private final IIngredient input;
		private final Float hits;
		
		public Add(String name, ItemStack output, IIngredient input, Float hits) {
			this.name = new ResourceLocation(HammerAndVil.MODID, name);
			this.output = output;
			this.input = input;
			this.hits  = hits;
		}
		
		@Override
		public void apply() {
			ItemStack result = VilRecipes.getVilResult(CraftTweakerMC.getItemStack(input));
			if (result == ItemStack.EMPTY) {
				VilRecipes.addVilRecipe(name.toString(), output, CraftTweakerMC.getItemStack(input), hits);
			} else {
				CraftTweakerAPI.logError("Recipe already exists for the name " + name.toString());

			}
		}

		@Override
		public String describe() {
			CraftTweakerAPI.logDefault("input: " + CraftTweakerMC.getItemStack(input) + " output: " + output);
			return "Added vil recipe for " + output.getItem();	
		}
		
	}
}