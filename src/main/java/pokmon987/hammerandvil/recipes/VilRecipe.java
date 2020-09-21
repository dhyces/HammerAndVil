package pokmon987.hammerandvil.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class VilRecipe implements IVilRecipe {

	private final String name;
	private final ItemStack output;
	private final NonNullList<ItemStack> inputs;
	private final int hits;
	private final List<ItemStack> tool;
	
	public VilRecipe(String nameIn, ItemStack outputIn, NonNullList<ItemStack> inputsIn, int hitsIn, ItemStack toolIn) {
		this(nameIn, outputIn, inputsIn, hitsIn, addToAList(toolIn));
	}
	
	public VilRecipe(String nameIn, ItemStack outputIn, NonNullList<ItemStack> inputsIn, int hitsIn, List<ItemStack> toolIn) {
		this.name = nameIn;
		this.output = outputIn;
		this.inputs = inputsIn;
		this.hits = hitsIn;
		this.tool = toolIn;
	}
	
	public static List<ItemStack> addToAList(ItemStack stack) {
		List<ItemStack> stackList = new ArrayList<>();
		stackList.add(stack);
		return stackList;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public ItemStack getOutput() {
		return this.output;
	}
	
	public NonNullList<ItemStack> getInputs() {
		return this.inputs;
	}
	
	@Override
	public int getHits() {
		return this.hits;
	}
	
	@Override
	public List<ItemStack> getTool() {
		return this.tool;
	}
	
	public boolean matches(final NonNullList<ItemStack> matchingList) {
		if (inputs.size() != matchingList.size()) {return false;}
		List<ItemStack> list = new ArrayList<>();
		for (ItemStack stack : matchingList) {
			list.add(stack);
		}
		for (ItemStack stack : inputs) {
			for (ItemStack recipeStack : list) {
				if (ItemStack.areItemStacksEqual(stack, recipeStack)) {
					list.remove(recipeStack);
					break;
				}
			}

		}
		return list.isEmpty();
	}
	
	@Override
	public String toString() {
		String ret = "";
		ret += this.name + " ";
		for (ItemStack item : this.inputs) {ret += item + " ";}
		ret += this.output + " ";
		ret += this.tool;
		return ret;
	}
}