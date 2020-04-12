package pokmon987.hammerandvil.integration.jei;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import pokmon987.hammerandvil.items.ModItems;

public class VilRecipeWrapper implements IRecipeWrapper {

	private final List<ItemStack> inputAndTool = new ArrayList<>();
	private final ItemStack output;
	private final float hits;
	
	public VilRecipeWrapper(ItemStack input, ItemStack output, ItemStack tool, Float hits) {
		tool = tool.isEmpty() ? new ItemStack(ModItems.itemHammer) : tool;
		this.inputAndTool.add(0, input);
		this.inputAndTool.add(1, tool);
		this.output = output;
		this.hits = (float)hits;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(VanillaTypes.ITEM, inputAndTool);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer fontRenderer = minecraft.fontRenderer;
		fontRenderer.drawString(String.valueOf((short)hits) + " hits", 53, 3, Color.BLACK.getRGB());
	}
}