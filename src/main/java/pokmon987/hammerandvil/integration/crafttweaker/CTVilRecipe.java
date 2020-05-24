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
		private final NonNullList<NonNullList<ItemStack>> inputs;
		private final Float hits;
		private final IIngredient tool;
		
		public Add(String name, ItemStack output, IIngredient[] inputs, Float hits, IIngredient tool) {
			this.name = new ResourceLocation(HammerAndVil.MODID, name);
			this.output = output;
			this.inputs = NonNullList.<NonNullList<ItemStack>>create();
			for (int i = 0; i < inputs.length; i++) {
				NonNullList<ItemStack> inputSlot = NonNullList.<ItemStack>create();
				if (inputs[i] instanceof IOreDictEntry) {
					ItemStack[] inputStacks = CraftTweakerMC.getItemStacks(inputs[i].getItemArray());
					for (int j = 0; j < inputStacks.length; j++) {
						inputSlot.add(inputStacks[j]);
					}
				} else {
					inputSlot.add(CraftTweakerMC.getItemStack(inputs[i]));
				}
				this.inputs.add(inputSlot);
			}
			this.hits  = hits;
			this.tool = tool;
		}
		
		public boolean isApplicable(NonNullList<ItemStack> inputsIn, ItemStack... stacks) {
			boolean result = new VilRecipes().getNameOutList().containsKey(name.toString());
			System.out.println(result);
			for (int i = 0; i < stacks.length; i++) {
				ItemStack requiredTool = VilRecipes.getToolForRecipe(stacks[i], inputsIn);
				if (!result && requiredTool.isEmpty()) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public void apply() {
			
			ItemStack[] toolStack = new ItemStack[1];
			NonNullList<ItemStack> inputStacks = NonNullList.<ItemStack>create();
			
			if (tool instanceof IOreDictEntry) {
				toolStack = CraftTweakerMC.getItemStacks(tool.getItemArray());
			} else {
				toolStack[0] = CraftTweakerMC.getItemStack(tool);
			}
			
			for (int j = 0; j < 3; j++) {
				if (inputs.get(j).size() > 1) {
					
				} else {
					inputStacks.add(inputs.get(j).get(0));
				}
			}
			
			
				
			
			if ((!EqualCheck.areEqual(toolStack[i], requiredTool))) {
				for (ItemStack input : inputs) {
					if (inputs instanceof IOreDictEntry) {
						VilRecipes.addVilRecipe(name.toString(), output, inputs, hits, toolStack);
					} else {
						VilRecipes.addVilRecipe(name.toString(), output, inputs, hits, toolStack);
					}
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