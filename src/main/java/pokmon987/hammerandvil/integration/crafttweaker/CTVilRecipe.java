package pokmon987.hammerandvil.integration.crafttweaker;


import java.util.ArrayList;
import java.util.List;

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
import pokmon987.hammerandvil.recipes.VilRecipe;
import pokmon987.hammerandvil.recipes.VilRecipes;
import pokmon987.hammerandvil.util.EqualCheck;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods." + HammerAndVil.MODID + ".Vil")
public class CTVilRecipe {
	
	@ZenMethod
	public static void addRecipe(String name, IItemStack output, IIngredient[] inputs, int hits, @Nullable IIngredient tool) {
		CraftTweaker.LATE_ACTIONS.add(new Add(name, CraftTweakerMC.getItemStack(output), inputs, hits, tool));
	}
	
	private static class Add implements IAction {
		private final ResourceLocation name;
		private final ItemStack output;
		private final IIngredient[] inputs;
		private final int hits;
		private final IIngredient tool;
		
		public Add(String name, ItemStack output, IIngredient[] inputs, int hits, IIngredient tool) {
			this.name = new ResourceLocation(HammerAndVil.MODID, name);
			this.output = output;
			this.inputs = inputs;
			if (inputs.length > 3) {CraftTweakerAPI.logError("Too many ingredients. The maximum is 3. The input is: " + inputs.length);}
//			for (int i = 0; i <= 3; i++) {
//				NonNullList<ItemStack> inputSlot = NonNullList.<ItemStack>create();
//				if (inputs[i] instanceof IOreDictEntry) {
//					ItemStack[] inputStacks = CraftTweakerMC.getItemStacks(inputs[i].getItemArray());
//					for (int j = 0; j < inputStacks.length; j++) {
//						inputSlot.add(inputStacks[j]);
//					}
//				} else {
//					inputSlot.add(CraftTweakerMC.getItemStack(inputs[i]));
//				}
//				this.inputs.add(inputSlot);
//			}
			this.hits  = hits;
			this.tool = tool;
		}
		
//		private boolean isApplicable(String nameIn, ItemStack... stacks) {
//			boolean result = true;
//			for (VilRecipe recipe : VilRecipes.recipes) {
//				if (recipe.getTool()) {
//					
//				}
//			}
//			boolean result = new VilRecipes().getNameOutList().containsKey(name.toString());
//			for (int i = 0; i < stacks.length; i++) {
//				ItemStack requiredTool = VilRecipes.compareTool(stacks[i], nameIn);
//				if (!result && requiredTool.isEmpty()) {
//					return true;
//				}
//			}
//			return false;
//		}
		
		private boolean areAnyOre(IIngredient[] inputArray) {
			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] instanceof IOreDictEntry) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public void apply() {
			if (this.inputs.length > 3) {return;}
			ItemStack[] toolStack = new ItemStack[1];
//			NonNullList<ItemStack> inputStacks = NonNullList.<ItemStack>create();
			
			if (tool instanceof IOreDictEntry) {
				toolStack = CraftTweakerMC.getItemStacks(tool.getItemArray());
			} else {
				toolStack[0] = CraftTweakerMC.getItemStack(tool);
			}
			
//			for (int j = 0; j < 3; j++) {
//				if (inputs.get(j).size() > 1) {
//					
//				} else {
//					inputStacks.add(inputs.get(j).get(0));
//				}
//			}
			
			if (areAnyOre(this.inputs)) {
				IIngredient[] inputArray = new IIngredient[3];
				for (int i = 0; i < this.inputs.length; i++) {
					inputArray[i] = this.inputs[i];
				}
				for (int i = 0; i < inputArray[0].getItemArray().length; i++) {
					NonNullList<ItemStack> inputStacks = NonNullList.<ItemStack>create();
					inputStacks.add(CraftTweakerMC.getItemStack(inputArray[0].getItemArray()[i]));
					if (inputArray[1] != null) {
					for (int j = 0; j < inputArray[1].getItemArray().length; j++) {
						inputStacks.add(CraftTweakerMC.getItemStack(inputArray[1].getItemArray()[j]));
						if (inputArray[2] != null) {
						for (int k = 0; k <= inputArray[2].getItemArray().length; k++) {
							inputStacks.add(CraftTweakerMC.getItemStack(inputArray[2].getItemArray()[k]));
//							if (isApplicable(inputStacks, toolStack)) {
								new VilRecipes().addVilRecipe(name.toString(), output, inputStacks, hits, toolStack);
//							} else {
//								CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + inputArray[2].getItemArray()[k].getDisplayName() + " " + tool);
//							}
						}
						} else {
//							if (isApplicable(inputStacks, toolStack)) {
								new VilRecipes().addVilRecipe(name.toString(), output, inputStacks, hits, toolStack);
//							} else {
//								CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + inputArray[1].getItemArray()[j].getDisplayName() + " " + tool);
//							}
						}
					}
					} else {
//						if (isApplicable(inputStacks, toolStack)) {
							new VilRecipes().addVilRecipe(name.toString() + i, output, inputStacks, hits, toolStack);
							inputStacks.forEach(action -> CraftTweakerAPI.logDefault(action.getDisplayName()));
//						} else {
//							CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + inputArray[0].getItemArray()[i].getDisplayName() + " " + tool);
//						}
					}
				}
			} else {
				NonNullList<ItemStack> inputStacks = NonNullList.<ItemStack>create();
				for (int i = 0; i < this.inputs.length; i++) {
					inputStacks.add(CraftTweakerMC.getItemStack(this.inputs[i]));
				}
//				if (isApplicable(inputStacks, toolStack)) {
					new VilRecipes().addVilRecipe(name.toString(), output, inputStacks, hits, toolStack);
//				} else {
//					CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + inputStacks + " " + tool);
//				}
			}
		}

		@Override
		public String describe() {
			CraftTweakerAPI.logDefault("inputs: " + inputs + " output: " + output + " tool: " + tool);
			return "Added vil recipe for " + output.getItem().getRegistryName();	
		}
		
	}
}