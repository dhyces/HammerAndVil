package pokmon987.hammerandvil.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokmon987.hammerandvil.items.ModItems;
import pokmon987.hammerandvil.util.EqualCheck;

@SideOnly(Side.CLIENT)
public class VilRecipes {
	
	public static List<IVilRecipe> recipes = new ArrayList<>();
	
	public void registerRecipes() {
//		addVilRecipe(HammerAndVil.MODID + ":whiteConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.WHITE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.WHITE.getMetadata()), 3, ItemStack.EMPTY);
	}
	
	public static void addVilRecipe(String name, ItemStack output, NonNullList<ItemStack> inputs, int hits, ItemStack... tool) {
		addVilRecipe(name, output, inputs, hits, Arrays.<ItemStack>asList(tool));
	}
	
//	public void addVilRecipe(String name, ItemStack output, ItemStack input, int hits, ItemStack tool) {
//		addVilRecipe(name, output, input, hits, tool);
//	}
//	
//	public void addVilRecipe(String name, ItemStack output, ItemStack input, int hits, List<ItemStack> tool) {
//		addVilRecipe(name, output, input, hits, tool);
//	}
	
	public static void addVilRecipe(String name, ItemStack output, NonNullList<ItemStack> inputs, int hits, List<ItemStack> tool) {
		List<ItemStack> toolIn = new ArrayList<>();
		if (tool.isEmpty()) {
			toolIn.add(new ItemStack(ModItems.itemHammer));
		} else {
			toolIn.addAll(tool);
		}
		recipes.add(new VilRecipe(name, output, inputs, hits, toolIn));
	}
	
	public static void addOreVilRecipe(String name, ItemStack output, NonNullList<NonNullList<ItemStack>> inputs, int hits, List<ItemStack> tool) {
		List<ItemStack> toolIn = new ArrayList<>();
		if (tool.isEmpty()) {
			toolIn.add(new ItemStack(ModItems.itemHammer));
		} else {
			toolIn.addAll(tool);
		}
		recipes.add(new OreVilRecipe(name, output, inputs, hits, toolIn));
	}
	
	/** Slightly less bad, arguably. Still bad though, should be made better*/
	public static IVilRecipe getRecipe(final NonNullList<ItemStack> inputs, ItemStack tool) {
		if (inputs.isEmpty()) {return null;}
		List<IVilRecipe> newRecipes = recipes.stream().filter(c -> c.getTool().stream().anyMatch(v -> ItemStack.areItemsEqualIgnoreDurability(v, tool))).collect(Collectors.toList());
		System.out.println(newRecipes.size());
		for (IVilRecipe oldRecipe : newRecipes) {
			if (oldRecipe instanceof VilRecipe) {
				VilRecipe recipe = (VilRecipe)oldRecipe;
				System.out.println(recipe.getInputs().size());
				int[] indexToIgnore = new int[] {4, 4, 4};
				int matching = 0;
				if (recipe.matches(inputs)) {
					return recipe;
				}
//				for (ItemStack stack : inputs) {
//					for (int i = 0; i < recipe.getInputs().size(); i++) {
//						if (i != indexToIgnore[i]) {
//							ItemStack input = recipe.getInputs().get(i);
//							System.out.println(input + " " + stack);
//							if (ItemStack.areItemStacksEqual(stack, input)) {
//								indexToIgnore[i] = i;
//								matching += 1;
//							}
//						}
//					}
//				}
				System.out.println(indexToIgnore);
				System.out.println(matching);
				System.out.println(recipe);
				if (matching == recipe.getInputs().size() && recipe.getInputs().size() == inputs.size()) {
					return recipe;
				}
			} else if (oldRecipe instanceof OreVilRecipe) {
				
			}
		}
		return null;
	}
	
	public static ItemStack compareTool(String name, ItemStack tool) {
		for (IVilRecipe entry : recipes) {
			if (entry.getName() == name) {
				for (ItemStack toolEntry : entry.getTool()) {
					if (EqualCheck.areEqual(tool, toolEntry)) {
						return tool;
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static boolean isTool(ItemStack tool) {
		for (IVilRecipe recipe : recipes) {
			if (EqualCheck.areEqual(tool, recipe.getTool())) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public static boolean oreRecipeExists(NonNullList<NonNullList<ItemStack>> inputs, List<ItemStack> tools) {
		List<OreVilRecipe> list = listToOreVilRecipeList(recipes.stream().filter(c -> c instanceof OreVilRecipe).collect(Collectors.toList()));
		return list.stream().anyMatch(c -> c.getInputs().stream().allMatch(v -> inputs.stream().anyMatch(b -> v.stream().anyMatch(x -> b.stream().anyMatch(z -> ItemStack.areItemStacksEqual(z, x))))) && c.getTool().stream().anyMatch(t -> tools.stream().anyMatch(y -> ItemStack.areItemStacksEqual(y, t))));
	}
	
	public static boolean normRecipeExists(NonNullList<ItemStack> inputs, List<ItemStack> tools) {
		return tools.stream().anyMatch(c -> getRecipe(inputs, c) != null);
	}
	
	public static boolean normRecipeExistsFromOre(NonNullList<NonNullList<ItemStack>> inputs, List<ItemStack> tools) {
		for (VilRecipe recipe : listToVilRecipeList(recipes.stream().filter(c -> c instanceof VilRecipe).collect(Collectors.toList()))) {
			for (ItemStack item : recipe.getInputs()) {
				if (inputs.stream().allMatch(c -> c.stream().anyMatch(v -> ItemStack.areItemStacksEqual(v, item)))) {
					if (tools.stream().anyMatch(c -> recipe.getTool().stream().anyMatch(v -> ItemStack.areItemStacksEqual(c, v)))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static List<VilRecipe> listToVilRecipeList(List<IVilRecipe> recipesIn) {
		List<VilRecipe> ret = new ArrayList<>();
		for (IVilRecipe recipe : recipesIn) {
			ret.add((VilRecipe)recipe);
		}
		return ret;
	}
	
	private static List<OreVilRecipe> listToOreVilRecipeList(List<IVilRecipe> recipesIn) {
		List<OreVilRecipe> ret = new ArrayList<>();
		for (IVilRecipe recipe : recipesIn) {
			ret.add((OreVilRecipe)recipe);
		}
		return ret;
	}
}