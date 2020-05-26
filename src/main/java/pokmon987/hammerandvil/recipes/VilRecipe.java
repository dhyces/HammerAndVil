package pokmon987.hammerandvil.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class VilRecipe {

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
	
	public String getName() {
		return this.name;
	}
	
	public ItemStack getOutput() {
		return this.output;
	}
	
	public NonNullList<ItemStack> getInputs() {
		return this.inputs;
	}
	
	public int getHits() {
		return this.hits;
	}
	
	public List<ItemStack> getTool() {
		return this.tool;
	}
}