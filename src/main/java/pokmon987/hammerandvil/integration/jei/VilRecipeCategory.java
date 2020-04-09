package pokmon987.hammerandvil.integration.jei;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import pokmon987.hammerandvil.HammerAndVil;

public class VilRecipeCategory implements IRecipeCategory<VilRecipeWrapper>{

	@Override
	public String getUid() {
		// TODO Auto-generated method stub
		return HammerAndVil.MODID;
	}

	@Override
	public String getTitle() {
		return "Vil";
	}

	@Override
	public String getModName() {
		return HammerAndVil.NAME;
	}

	@Override
	public IDrawable getBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, VilRecipeWrapper recipeWrapper, IIngredients ingredients) {
		
	}
	
}