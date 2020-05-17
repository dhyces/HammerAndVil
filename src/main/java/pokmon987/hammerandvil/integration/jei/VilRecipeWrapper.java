package pokmon987.hammerandvil.integration.jei;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class VilRecipeWrapper implements IRecipeWrapper {

	private final List<ItemStack> inputs = new ArrayList<>();
	private final List<ItemStack> tool = new ArrayList<>();
	private final ItemStack output;
	private final float hits;
	private final IJeiHelpers helpers;
	
	public VilRecipeWrapper(NonNullList<ItemStack> inputs, ItemStack output, List<ItemStack> tool, Float hits, IJeiHelpers helpers) {
		for (int i = 0; i < 3; i++) {
			if (i < inputs.size()) {
				this.inputs.add(inputs.get(i));
			} else {
				this.inputs.add(ItemStack.EMPTY);
			}
		}
		this.tool.addAll(tool);
		this.output = output;
		this.hits = hits;
		this.helpers = helpers;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		IStackHelper stackHelper = helpers.getStackHelper();
		List<List<ItemStack>> inputLists = stackHelper.expandRecipeItemStackInputs(inputs);
		inputLists.add(tool);
		ingredients.setInputLists(VanillaTypes.ITEM, inputLists);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	public List<ItemStack> getVilInputs() {
		return this.inputs;
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer fontRenderer = minecraft.fontRenderer;
		fontRenderer.drawString(String.valueOf((short)hits) + " hits", 53, 0, Color.BLACK.getRGB());
	}
}