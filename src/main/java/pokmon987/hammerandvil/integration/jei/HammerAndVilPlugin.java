package pokmon987.hammerandvil.integration.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.blocks.ModBlocks;

@JEIPlugin
public class HammerAndVilPlugin implements IModPlugin {
	
	public static final String vilCategoryUid = HammerAndVil.MODID + ".vil";
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new VilRecipeCategory(helper));
	}
	
	@Override
	public void register(IModRegistry registry) {
		IJeiHelpers helpers = registry.getJeiHelpers();
		registry.addRecipes(VilRecipeMaker.getVilRecipes(helpers), vilCategoryUid);
		
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.vil), vilCategoryUid);
	}
}