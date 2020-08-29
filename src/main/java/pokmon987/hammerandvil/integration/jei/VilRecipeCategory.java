package pokmon987.hammerandvil.integration.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.blocks.ModBlocks;

public class VilRecipeCategory implements IRecipeCategory<IRecipeWrapper>{

	private final IDrawable background;
	private final IDrawable icon;
	
	public VilRecipeCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(new ResourceLocation(HammerAndVil.MODID, "textures/gui/jei_vil.png"), 0, 0, 80, 36);
		this.icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.vil));
	}
	@Override
	public String getUid() {
		return HammerAndVil.MODID + ".vil";
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
		return this.background;
	}
	
	@Override
	public IDrawable getIcon() {
		return this.icon;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemsGroup = recipeLayout.getItemStacks();

		itemsGroup.init(0, true, 0, 0);
		itemsGroup.init(1, true, 18, 0);
		itemsGroup.init(2, true, 9, 18);
		itemsGroup.init(3, true, 35, -2); //Tool
		itemsGroup.init(4, false, 61, 9); //Result
		itemsGroup.set(ingredients);
	}
	
}