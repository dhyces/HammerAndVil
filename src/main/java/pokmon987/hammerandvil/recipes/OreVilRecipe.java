package pokmon987.hammerandvil.recipes;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class OreVilRecipe implements IVilRecipe {
	
	private final String name;
	private final ItemStack output;
	private final NonNullList<NonNullList<ItemStack>> inputs;
	private final int hits;
	private final List<ItemStack> tool;
	
	public OreVilRecipe(String nameIn, ItemStack outputIn, NonNullList<NonNullList<ItemStack>> inputsIn, int hitsIn, List<ItemStack> toolIn) {
		this.name = nameIn;
		this.output = outputIn;
		this.inputs = inputsIn;
		this.hits = hitsIn;
		this.tool = toolIn;
	}
	
	public boolean allMatch(List<ItemStack> itemStacks) {
		int included = 0;
		for (ItemStack stack : itemStacks) {
			if (getInputs().stream().anyMatch(c -> c.stream().anyMatch(x -> ItemStack.areItemStacksEqual(stack, x)))) {
				included += 1;
				continue;
			}
		}
		return included == itemStacks.size();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ItemStack getOutput() {
		return this.output;
	}

	public NonNullList<NonNullList<ItemStack>> getInputs() {
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

	@Override
	public String toString() {
		String ret = "";
		ret += this.name + " ";
		for (NonNullList<ItemStack> list : this.inputs) {for (ItemStack item : list) {ret += item + " ";}}
		ret += this.output + " ";
		ret += this.tool;
		return ret;
	}
}
