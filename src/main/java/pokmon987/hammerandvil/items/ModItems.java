package pokmon987.hammerandvil.items;

import net.minecraftforge.fml.common.registry.ForgeRegistries;
import pokmon987.hammerandvil.HammerAndVil;

public class ModItems {
	
	public static ItemHammer itemHammer;
		
	public static void init() {
		itemHammer = (ItemHammer)(new ItemHammer().setRegistryName("hammer"));
		itemHammer.setUnlocalizedName(HammerAndVil.MODID + ".hammer");
		
		ForgeRegistries.ITEMS.register(itemHammer);
	}
	
	
}