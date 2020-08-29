package pokmon987.hammerandvil.util;

import java.util.List;

import net.minecraft.item.ItemStack;

public class EqualCheck {
	/* Just checks if the items are the same, and (if it has metadata) have the same metadata*/
	public static boolean areEqual(ItemStack handTool, ItemStack requiredTool) {
		if (handTool.getItem() == requiredTool.getItem()) {
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