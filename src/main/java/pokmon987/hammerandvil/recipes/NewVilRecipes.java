package pokmon987.hammerandvil.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.items.ModItems;

public class NewVilRecipes {
	
	public static final List<VilRecipe> recipes = new ArrayList<>();
	
	public void registerRecipes() {
		addVilRecipe(HammerAndVil.MODID + ":whiteConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.WHITE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.WHITE.getMetadata()), 3, ItemStack.EMPTY);
	}
	
	public void addVilRecipe(String name, ItemStack output, NonNullList<NonNullList<ItemStack>> inputs, int hits, ItemStack tool) {
		addVilRecipe(name, output, inputs, hits, tool);
	}
	
	public void addVilRecipe(String name, ItemStack output, ItemStack input, int hits, ItemStack tool) {
		addVilRecipe(name, output, input, hits, tool);
	}
	
	public void addVilRecipe(String name, ItemStack output, ItemStack input, int hits, List<ItemStack> tool) {
		addVilRecipe(name, output, input, hits, tool);
	}
	
	public void addVilRecipe(String name, ItemStack output, NonNullList<NonNullList<ItemStack>> inputs, int hits, List<ItemStack> tool) {
		List<ItemStack> toolIn = new ArrayList<>();
		if (tool.isEmpty()) {
			toolIn.add(new ItemStack(ModItems.itemHammer));
		} else {
			toolIn.addAll(tool);
		}
		recipes.add(new VilRecipe(name, output, inputs, hits, toolIn));
	}
	
	public static String getName(NonNullList<ItemStack> inputs, ItemStack tool) {
		for (VilRecipe entry : recipes) {
			for (int i = 0; i < entry.getInputs().size(); i++) {
				if (OreDictionary.containsMatch(true, entry.getInputs().get(i), inputs.toArray(new ItemStack[3])) && entry.getTool().contains(tool)) {
					return entry.getName();
				}
			}
		}
		return null;
	}
	
	public static ItemStack getResult(String name) {
		for (VilRecipe entry : recipes) {
			if (entry.getName() == name) {
				return entry.getOutput();
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static int getHits(String name) {
		for (VilRecipe entry : recipes) {
			if (entry.getName() == name) {
				return entry.getHits();
			}
		}
		return 0;
	}
	
	public static ItemStack compareTool(String name, ItemStack tool) {
		for (VilRecipe entry : recipes) {
			if (entry.getName() == name) {
				for (ItemStack toolEntry : entry.getTool()) {
					if (ItemStack.areItemStacksEqual(toolEntry, tool)) {
						return tool;
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
}