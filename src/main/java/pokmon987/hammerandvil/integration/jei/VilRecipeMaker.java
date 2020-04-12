package pokmon987.hammerandvil.integration.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;
import pokmon987.hammerandvil.recipes.VilRecipes;

public class VilRecipeMaker {
	
	public static List<VilRecipeWrapper> getVilRecipes(IJeiHelpers helpers) {
		VilRecipes vilRecipes = new VilRecipes();
		Map<String, ItemStack> inputList = vilRecipes.getNameInList();
		Map<String, ItemStack> outputList = vilRecipes.getNameOutList();
		Map<String, ItemStack> toolList = vilRecipes.getToolList();
		Map<String, Float> hitList = vilRecipes.getHitList();
		
		List<VilRecipeWrapper> recipes = new ArrayList<>();
		
		for (Entry<String, ItemStack> entryIn : inputList.entrySet()) {
			for (Entry<String, ItemStack> entryOut : outputList.entrySet()) {
				if (entryIn.getKey() == entryOut.getKey()) {
					ItemStack tool = toolList.get(entryIn.getKey());
					Float hits = hitList.get(entryIn.getKey());
					VilRecipeWrapper recipe = new VilRecipeWrapper(entryIn.getValue(), entryOut.getValue(), tool, hits);
					recipes.add(recipe);
				}
			}
		}
		return recipes;
	}
}