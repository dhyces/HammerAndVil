package pokmon987.hammerandvil.recipes;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IVilRecipe {
	public String getName();
	
	public ItemStack getOutput();
	
	public int getHits();
	
	public List<ItemStack> getTool();
}
