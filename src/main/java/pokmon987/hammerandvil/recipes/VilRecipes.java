package pokmon987.hammerandvil.recipes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.sun.jna.platform.win32.Ole32;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreIngredient;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.items.ModItems;
import pokmon987.hammerandvil.util.EqualCheck;
import scala.collection.mutable.ArrayBuilder.ofBoolean;

public class VilRecipes {
	
	private static final Map<String, NonNullList<ItemStack>> nameInList = new HashMap<String, NonNullList<ItemStack>>();
	private static final Map<String, ItemStack> nameOutList = new HashMap<String, ItemStack>();
	private static final Map<String, List<ItemStack>> toolList = new HashMap<String, List<ItemStack>>();
	private static final Map<String, Float> hitList = new HashMap<String, Float>();
	private static NonNullList<ItemStack> organized = NonNullList.create();
	
	public VilRecipes() {
		
	}
	
	public static void registerRecipes() {
		addVilRecipe(HammerAndVil.MODID + ":whiteConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.WHITE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.WHITE.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":orangeConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.ORANGE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.ORANGE.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":magentaConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.MAGENTA.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.MAGENTA.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":lightBlueConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":yellowConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.YELLOW.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.YELLOW.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":limeConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.LIME.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIME.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":pinkConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.PINK.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PINK.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":grayConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.GRAY.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GRAY.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":lightGrayConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.SILVER.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.SILVER.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":cyanConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.CYAN.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":purpleConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.PURPLE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PURPLE.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":blueConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLUE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLUE.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":brownConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BROWN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BROWN.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":greenConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.GREEN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GREEN.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":redConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.RED.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.RED.getMetadata()), 3.0F, ItemStack.EMPTY);
		addVilRecipe(HammerAndVil.MODID + ":blackConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLACK.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLACK.getMetadata()), 3.0F, ItemStack.EMPTY);
	}
	/**This is used if the input is an ore dictionary entry*/
//	public static void addVilRecipe(String name, ItemStack output, String input, Float hits, @Nullable ItemStack tool) {
//		ItemStack toolIn = tool.isEmpty() ? new ItemStack(ModItems.itemHammer) : tool;
//		List<ItemStack> list = NonNullList.create();
//		list.add(toolIn);
//		addVilRecipe(name, output, input, hits, list);
//	}
	
	public static void addVilRecipe(String name, ItemStack output, ItemStack input, Float hits, @Nullable ItemStack tool) {
		ItemStack toolIn = tool.isEmpty() ? new ItemStack(ModItems.itemHammer) : tool;
		List<ItemStack> list = NonNullList.create();
		list.add(toolIn);
		addVilRecipe(name, output, input, hits, list);
	}
	
	public static void addVilRecipe(String name, ItemStack output, NonNullList<ItemStack> inputs, Float hits, @Nullable ItemStack... tool) {
		if (tool[0].isEmpty()) {
			tool[0] = new ItemStack(ModItems.itemHammer);
		}
		List<ItemStack> list = NonNullList.create();
		Collections.addAll(list, tool);
		addVilRecipe(name, output, inputs, hits, list);
	}
	
	/** */
	public static void addVilRecipe(String name, ItemStack output, ItemStack input, Float hits, @Nullable List<ItemStack> tool) {
		NonNullList<ItemStack> inputs = NonNullList.create();
		inputs.add(input);
		addVilRecipe(name, output, inputs, hits, tool);
	}
	
	public static void addVilRecipe(String name, ItemStack output, NonNullList<ItemStack> inputs, Float hits, @Nullable List<ItemStack> tool) {
		nameInList.put(name, inputs);
		nameOutList.put(name, output);
		hitList.put(name, hits);
		toolList.put(name, tool);
		System.out.println(inputs);
	}
	
	public static boolean checkNameExists(String name) {
		return false;
	}
	
	private static boolean compareNames(Ingredient inputs, Ingredient tool) {
		if (inputs instanceof OreIngredient) {}
		return true;
	}
	
	public static String getNameForRecipe(NonNullList<ItemStack> inputs, ItemStack tool) {
		for (Entry<String, NonNullList<ItemStack>> entry : nameInList.entrySet()) {
			if (checkIfExists(inputs, entry.getValue())) {
				for (Entry<String, List<ItemStack>> entry2 : toolList.entrySet()) {
					if (entry.getKey() == entry2.getKey() && EqualCheck.areEqual(tool, entry2.getValue())) {
						return entry.getKey();
					}
				}
			}
		}
		return null;
	}
	
	public static ItemStack getToolForRecipe(ItemStack tool, NonNullList<ItemStack> inputs) {
		for (Entry<String, NonNullList<ItemStack>> entry : nameInList.entrySet()) {
			//System.out.println("Entry: " + entry + ". Tool: " + tool);
			if (checkIfExists(inputs, entry.getValue())) {
				//System.out.println("Entry: " + entry + ". Tool: " + tool + ". ReqTool: " + getRequiredTool(input));
				for (Entry<String, List<ItemStack>> entry2 : toolList.entrySet()) {
					if (entry.getKey() == entry2.getKey() && EqualCheck.areEqual(tool, entry2.getValue())) {
						//System.out.println("Entry 1: " + entry + ". Entry 2: " + entry2 + ". Tool: " + tool + ". MapTool: " + mappedTool);
						return tool;
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static boolean isTool(ItemStack handTool) {
		for (Entry<String, List<ItemStack>> entry : toolList.entrySet()) {
			if (EqualCheck.areEqual(handTool, entry.getValue())) {
				return true;
			}
		}
		return false;
	}
	
	public static ItemStack getVilResult(NonNullList<ItemStack> inputs, ItemStack handTool) {
		if (inputs != null) {
			for (Entry<String, List<ItemStack>> toolEntry : toolList.entrySet()) {
				for (Entry<String, NonNullList<ItemStack>> entry : nameInList.entrySet()) {
					if (checkIfExists(inputs, entry.getValue()) && toolEntry.getKey() == entry.getKey() && EqualCheck.areEqual(handTool, toolEntry.getValue())) {
						for (Entry<String, ItemStack> entry2 : nameOutList.entrySet()) {
							if (entry.getKey() == entry2.getKey() && entry.getKey() == toolEntry.getKey() && entry2.getKey() == toolEntry.getKey()) {
								return entry2.getValue();
							}
						}
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static ItemStack getVilResultByName(String name) {
		return nameOutList.get(name);
	}
	
	public static Float getHitsRequiredByName(String name) {
		return hitList.get(name);
	}
	
	public static boolean checkIfExists(NonNullList<ItemStack> inputs, NonNullList<ItemStack> mapped) {
		if (!organized.isEmpty()) {organized.clear();}
		if (inputs.size() == mapped.size()) {
			for (int i = 0; i < mapped.size(); i++) {
				for (int j = 0; j < inputs.size(); j++) {
					if (ItemStack.areItemStacksEqual(inputs.get(j), mapped.get(i)) && organized.size() != inputs.size()) {
						organized.add(inputs.get(j));
					}
					if (organized.size() == inputs.size()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Map<String, NonNullList<ItemStack>> getNameInList() {
		return nameInList;
	}
	
	public Map<String, ItemStack> getNameOutList() {
		return nameOutList;
	}
	
	public Map<String, List<ItemStack>> getToolList() {
		return toolList;
	}
	
	public Map<String, Float> getHitList() {
		return hitList;
	}
}