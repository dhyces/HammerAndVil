package pokmon987.hammerandvil.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

public class VilRecipes {
	
	private static final Map<String, ItemStack> nameList = Maps.<String, ItemStack>newHashMap();
	private static final Map<ItemStack, ItemStack> vilList = Maps.<ItemStack, ItemStack>newHashMap();
	private static final Map<ItemStack, Float> hitList = Maps.<ItemStack, Float>newHashMap();
	
	public VilRecipes() {
		
	}
	
	public static void registerRecipes() {
		addVilRecipe("blackConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLACK.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLACK.getMetadata()), 3.0F);
		addVilRecipe("blackConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLUE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLUE.getMetadata()), 3.0F);
		addVilRecipe("blackConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.CYAN.getMetadata()), 3.0F);
		addVilRecipe("blackConcrete", new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT), 3.0F);
	}
	
	public static void addVilRecipe(String name, ItemStack output, ItemStack input, Float hits) {
		nameList.put(name, output);
		vilList.put(input, output);
		hitList.put(input, hits);
	}
	
	public static ItemStack getVilResultByName(String name) {
		for (Entry<String, ItemStack> entry : nameList.entrySet()) {
			if (name == entry.getKey()) {
				return entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static ItemStack getVilResult(ItemStack input) {
		for (Entry<ItemStack, ItemStack> entry : vilList.entrySet()) {
			if (input.getItem() == entry.getKey().getItem() && input.getMetadata() == entry.getKey().getMetadata()) {
				//System.out.println("ItemStack: " + input + " " + entry.getKey() + " Metadata: " + input.getMetadata() + " " + entry.getKey().getMetadata());
				return entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static Float getHitsRequiredForStack(ItemStack stack) {
		 for (Entry<ItemStack, Float> entry : hitList.entrySet()) {
		 	if (stack.getItem() == entry.getKey().getItem()) {
		 		return entry.getValue();
		 	}
		}
		 return null;
	}
}