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
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.util.EqualCheck;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods." + HammerAndVil.MODID + ".Vil")
public class CTVilRecipe {
	
	@ZenMethod
	public static void addRecipe(String name, IItemStack output, IIngredient[] inputs, Float hits, @Nullable IIngredient tool) {
		CraftTweaker.LATE_ACTIONS.add(new Add(name, CraftTweakerMC.getItemStack(output), inputs, hits, tool));
	}
	
	private static class Add implements IAction {
		private final ResourceLocation name;
		private final ItemStack output;
		private final NonNullList<ItemStack> inputs;
		private final Float hits;
		private final IIngredient tool;
		
		public Add(String name, ItemStack output, IIngredient[] inputs, Float hits, IIngredient tool) {
			this.name = new ResourceLocation(HammerAndVil.MODID, name);
			this.output = output;
			this.inputs = NonNullList.create();
			for (int i = 0; i < inputs.length; i++) {
				this.inputs.add(CraftTweakerMC.getItemStack(inputs[i]));
			}
			this.hits  = hits;
			this.tool = tool;
		}
		
		@Override
		public void apply() {
			boolean result = new VilRecipes().getNameOutList().containsKey(name.toString());
			ItemStack toolStack = CraftTweakerMC.getItemStack(tool);
			ItemStack requiredTool = VilRecipes.getToolForRecipe(toolStack, inputs);
			if ((result == false || !EqualCheck.areEqual(toolStack, requiredTool))) {
				if (tool instanceof IOreDictEntry) {
					VilRecipes.addVilRecipe(name.toString(), output, inputs, hits, CraftTweakerMC.getItemStacks((tool.getItemArray())));
				} else if (inputs instanceof IOreDictEntry) {
					VilRecipes.addVilRecipe(name.toString(), output, inputs, hits, CraftTweakerMC.getItemStacks((tool.getItemArray())));
				} else {
					VilRecipes.addVilRecipe(name.toString(), output, inputs, hits, toolStack);
				}
			} else {
				CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + inputs + " " + tool);
			}
		}

		@Override
		public String describe() {
			CraftTweakerAPI.logDefault("inputs: " + inputs + " output: " + output + " tool: " + tool);
			return "Added vil recipe for " + output.getItem().getRegistryName();	
		}
		
	}
}