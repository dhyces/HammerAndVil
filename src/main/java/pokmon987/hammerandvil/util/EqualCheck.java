package pokmon987.hammerandvil.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import pokmon987.hammerandvil.items.ModItems;

public class EqualCheck {
	
	public static boolean areEqual(ItemStack handTool, ItemStack requiredTool) {
		if (handTool.getItem() == ModItems.itemHammer && handTool.getItem() == requiredTool.getItem()) {
			return true;
		} else if (handTool.getItem() == requiredTool.getItem()) {
			if (!requiredTool.getHasSubtypes()) {
				return true;
			} else if (handTool.getMetadata() == requiredTool.getMetadata()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean areEqual(ItemStack handTool, List<ItemStack> requiredTool) {
			for (int i = 0; i < requiredTool.size(); i++) {
				if (handTool.getItem() == requiredTool.get(i).getItem()) {
					if (!handTool.getHasSubtypes()) {
						return true;
					} else if (handTool.getMetadata() == requiredTool.get(i).getMetadata()) {
						return true;
					}
				}
			}
		return false;
	}
}