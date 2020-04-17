package pokmon987.hammerandvil.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.items.ModItems;
import pokmon987.hammerandvil.util.MetaCheck;

public class VilRecipes {
	
	private static final Map<String, ItemStack> nameInList = new HashMap<String, ItemStack>();
	private static final Map<String, ItemStack> nameOutList = new HashMap<String, ItemStack>();
	private static final Map<String, ItemStack> toolList = new HashMap<String, ItemStack>();
	private static final Map<String, Float> hitList = new HashMap<String, Float>();
	
	public VilRecipes() {
		
	}
	
	public static void registerRecipes() {
		addVilRecipe(HammerAndVil.MODID + ":blackConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLACK.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLACK.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":blueConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLUE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLUE.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":cyanConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.CYAN.getMetadata()), 3.0F, ItemStack.EMPTY);
	}
	
	public static void addVilRecipe(String name, ItemStack output, ItemStack input, Float hits, @Nullable ItemStack tool) {
		nameInList.put(name, input);
		nameOutList.put(name, output);
		hitList.put(name, hits);
		toolList.put(name, tool);
	}
	
	public static ItemStack getVilResultByName(String name) {
		return nameOutList.get(name);
	}
	
	public static boolean checkNameExists(String name) {
		return false;
	}
	
	public static String getNameForRecipe(ItemStack input, ItemStack tool) {
		for (Entry<String, ItemStack> entry : nameInList.entrySet()) {
			if (ItemStack.areItemStacksEqual(input, entry.getValue())) {
				for (Entry<String, ItemStack> entry2 : toolList.entrySet()) {
					ItemStack mappedTool = entry2.getValue() != ItemStack.EMPTY ? entry2.getValue() : new ItemStack(ModItems.itemHammer);
					if (entry.getKey() == entry2.getKey() && MetaCheck.hasEqualMeta(tool, mappedTool)) {
						return entry.getKey();
					}
				}
			}
		}
		return null;
	}
	
	public static ItemStack getToolForRecipe(ItemStack tool, ItemStack input) {
		for (Entry<String, ItemStack> entry : nameInList.entrySet()) {
			//System.out.println("Entry: " + entry + ". Tool: " + tool);
			if (input.getItem() == entry.getValue().getItem() && input.getMetadata() == entry.getValue().getMetadata()) {
				//System.out.println("Entry: " + entry + ". Tool: " + tool + ". ReqTool: " + getRequiredTool(input));
				for (Entry<String, ItemStack> entry2 : toolList.entrySet()) {
					ItemStack mappedTool = entry2.getValue() != ItemStack.EMPTY ? entry2.getValue() : new ItemStack(ModItems.itemHammer);
					if (entry.getKey() == entry2.getKey() && MetaCheck.hasEqualMeta(tool, mappedTool)) {
						//System.out.println("Entry 1: " + entry + ". Entry 2: " + entry2 + ". Tool: " + tool + ". MapTool: " + mappedTool);
						return mappedTool;
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static boolean isTool(ItemStack handTool) {
		for (Entry<String, ItemStack> entry : toolList.entrySet()) {
			ItemStack mappedTool = entry.getValue() != ItemStack.EMPTY ? entry.getValue() : new ItemStack(ModItems.itemHammer);
			if (MetaCheck.hasEqualMeta(handTool, mappedTool)) {
				return true;
			}
		}
		return false;
	}
	
	public static ItemStack getVilResult(ItemStack input, ItemStack handTool) {
		for (Entry<String, ItemStack> toolEntry : toolList.entrySet()) {
			for (Entry<String, ItemStack> entry : nameInList.entrySet()) {
				ItemStack mappedTool = toolEntry.getValue() != ItemStack.EMPTY ? toolEntry.getValue() : new ItemStack(ModItems.itemHammer);
				if (ItemStack.areItemStacksEqual(input, entry.getValue()) && toolEntry.getKey() == entry.getKey() && MetaCheck.hasEqualMeta(handTool, mappedTool)) {
					for (Entry<String, ItemStack> entry2 : nameOutList.entrySet()) {
						//System.out.println("entry 1: " + entry + ". Entry 2: " + entry2);
						if (entry.getKey() == entry2.getKey() && entry.getKey() == toolEntry.getKey() && entry2.getKey() == toolEntry.getKey()) {
							return entry2.getValue();
						}
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static Float getHitsRequiredByName(String name) {
		for (Entry<String, Float> entry : hitList.entrySet()) {
		 	if (name == entry.getKey()) {
		 		return entry.getValue();
		 	}
		}
		 return null;
	}
	
	public Map<String, ItemStack> getNameInList() {
		return nameInList;
	}
	
	public Map<String, ItemStack> getNameOutList() {
		return nameOutList;
	}
	
	public Map<String, ItemStack> getToolList() {
		return toolList;
	}
	
	public Map<String, Float> getHitList() {
		return hitList;
	}
}