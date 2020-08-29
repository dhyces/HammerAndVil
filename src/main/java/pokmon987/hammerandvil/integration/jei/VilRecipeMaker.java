package pokmon987.hammerandvil.integration.jei;

import java.util.ArrayList;
import java.util.List;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import pokmon987.hammerandvil.recipes.IVilRecipe;
import pokmon987.hammerandvil.recipes.OreVilRecipe;
import pokmon987.hammerandvil.recipes.VilRecipe;
import pokmon987.hammerandvil.recipes.VilRecipes;

public class VilRecipeMaker {
	
	public static List<IRecipeWrapper> getVilRecipes(IJeiHelpers helpers) {
		List<IVilRecipe> vilRecipes = VilRecipes.recipes;
		
		List<IRecipeWrapper> recipes = new ArrayList<>();
		
		for (IVilRecipe recipeType : vilRecipes) {
			if (recipeType instanceof VilRecipe) {
				NonNullList<ItemStack> inputs = ((VilRecipe)recipeType).getInputs();
				ItemStack output = recipeType.getOutput();
				List<ItemStack> tool = recipeType.getTool();
				int hits = recipeType.getHits();
				VilRecipeWrapper recipe = new VilRecipeWrapper(inputs, output, tool, hits, helpers);
				recipes.add(recipe);
			} else if (recipeType instanceof OreVilRecipe) {
				NonNullList<NonNullList<ItemStack>> inputs = ((OreVilRecipe)recipeType).getInputs();
				ItemStack output = recipeType.getOutput();
				List<ItemStack> tool = recipeType.getTool();
				int hits = recipeType.getHits();
				OreVilRecipeWrapper recipe = new OreVilRecipeWrapper(inputs, output, tool, hits, helpers);
				recipes.add(recipe);
			}
			
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