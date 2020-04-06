package pokmon987.hammerandvil.util;

import net.minecraft.item.ItemStack;
import pokmon987.hammerandvil.items.ModItems;

public class MetaCheck {
	public static boolean hasEqualMeta(ItemStack handTool, ItemStack requiredTool) {
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
}