package pokmon987.hammerandvil.integration.crafttweaker;

import java.util.Arrays;
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
import pokmon987.hammerandvil.items.ModItems;
import pokmon987.hammerandvil.recipes.IVilRecipe;
import pokmon987.hammerandvil.recipes.VilRecipes;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods." + HammerAndVil.MODID + ".Vil")
public class CTVilRecipe {
	
	@ZenMethod
	public static void addRecipe(String name, IItemStack output, IIngredient[] inputs, int hits, @Nullable IIngredient tool) {
		if (inputs.length > 3) {CraftTweakerAPI.logError("ERROR: more than 3 inputs.");return;}
		if (Arrays.stream(inputs).anyMatch(c -> c instanceof IOreDictEntry)) {
			CraftTweaker.LATE_ACTIONS.add(new Add(name, CraftTweakerMC.getItemStack(output), inputs, hits, tool, true));
			return;
		}
		CraftTweaker.LATE_ACTIONS.add(new Add(name, CraftTweakerMC.getItemStack(output), inputs, hits, tool, false));
	}
	
	private static class Add implements IAction {
		private final ResourceLocation name;
		private final ItemStack output;
		private final IIngredient[] inputs;
		private final int hits;
		private final IIngredient tool;
		private final boolean oredict;
		
		public Add(String name, ItemStack output, IIngredient[] inputs, int hits, IIngredient tool, boolean oredict) {
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
			this.oredict = oredict;
		}
		
		private boolean nameExists() {
			for (IVilRecipe recipe : VilRecipes.recipes) {
				if (recipe.getName() == this.name.toString()) {
					return true;
				}
			}
			return false;
		}
		
		private boolean isApplicable(ItemStack... stacks) {
			for (int i = 0; i < stacks.length; i++) {
				ItemStack requiredTool = VilRecipes.compareTool(this.name.toString(), stacks[i]);
				if (!nameExists() && requiredTool.isEmpty()) {
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
			} else if (tool == null) {
				toolStack[0] = new ItemStack(ModItems.itemHammer);
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
			NonNullList<ItemStack> normList = NonNullList.<ItemStack>create();
			if (!oredict) {
				for (IIngredient ingredient : this.inputs) {
					normList.add(CraftTweakerMC.getItemStack(ingredient));
				}
			}
			NonNullList<NonNullList<ItemStack>> oreList = NonNullList.create();
			for (IIngredient ingredient : this.inputs) {
				NonNullList<ItemStack> stackList = NonNullList.create();
				if (ingredient instanceof IOreDictEntry) {
					stackList.addAll(Arrays.asList(CraftTweakerMC.getItemStacks(((IOreDictEntry)ingredient).getItems())));
				} else {
					stackList.add(CraftTweakerMC.getItemStack(ingredient));
				}
				oreList.add(stackList);
			}
			if (!oredict) {
				if (isApplicable(toolStack) && !VilRecipes.normRecipeExists(normList, Arrays.asList(toolStack)) && !VilRecipes.oreRecipeExists(oreList, Arrays.asList(toolStack))) {
					VilRecipes.addVilRecipe(name.toString(), output, normList, hits, toolStack);
				} else {
					CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + normList + " " + tool);
				}
			} else {
				if (isApplicable(toolStack) && !VilRecipes.normRecipeExistsFromOre(oreList, Arrays.asList(toolStack)) && !VilRecipes.oreRecipeExists(oreList, Arrays.asList(toolStack))) {
					VilRecipes.addOreVilRecipe(name.toString(), output, oreList, hits, Arrays.asList(toolStack));
				} else {
					CraftTweakerAPI.logError("The recipe name already exists: " + name.toString() + ". Or a recipe with that input and tool already exists: " + oreList + " " + tool);
				}
				
			}
		}

		@Override
		public String describe() {
			CraftTweakerAPI.logDefault("inputs: " + inputs + " output: " + output + " tool: " + tool);
			return "Added vil recipe for " + output.getItem().getRegistryName();	
		}
		
	}
}