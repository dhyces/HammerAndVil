package pokmon987.hammerandvil.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemHammer extends Item {
	
	public ItemHammer() {
		setCreativeTab(CreativeTabs.TOOLS);
		setMaxStackSize(1);
		//add config here to set the item's max damage
		setMaxDamage(1000);
	}
}