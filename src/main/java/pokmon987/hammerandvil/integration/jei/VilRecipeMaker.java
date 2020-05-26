package pokmon987.hammerandvil.integration.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import pokmon987.hammerandvil.recipes.VilRecipe;
import pokmon987.hammerandvil.recipes.VilRecipes;

public class VilRecipeMaker {
	
	public static List<VilRecipeWrapper> getVilRecipes(IJeiHelpers helpers) {
		List<VilRecipe> vilRecipes = VilRecipes.recipes;
		
		List<VilRecipeWrapper> recipes = new ArrayList<>();
		
		for (VilRecipe recipeType : vilRecipes) {
			NonNullList<ItemStack> inputs = recipeType.getInputs();
			ItemStack output = recipeType.getOutput();
			List<ItemStack> tool = recipeType.getTool();
			int hits = recipeType.getHits();
			VilRecipeWrapper recipe = new VilRecipeWrapper(inputs, output, tool, hits, helpers);
			recipes.add(recipe);
		}
		
//		for (Entry<String, NonNullList<ItemStack>> entryIn : vilRecipes.entrySet()) {
//			for (Entry<String, ItemStack> entryOut : outputList.entrySet()) {
//				if (entryIn.getKey() == entryOut.getKey()) {
//					List<ItemStack> tool = toolList.get(entryIn.getKey());
//					Float hits = hitList.get(entryIn.getKey());
//					VilRecipeWrapper recipe = new VilRecipeWrapper(entryIn.getValue(), entryOut.getValue(), tool, hits, helpers);
//					recipes.add(recipe);
//				}
//			}
//		}
		return recipes;
	}
}