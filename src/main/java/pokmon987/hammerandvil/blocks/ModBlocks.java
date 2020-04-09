package pokmon987.hammerandvil.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBlocks {
	public static BlockVil vil;
	public static ItemBlock itemVil;
	
	public static void init() {
		vil = new BlockVil();
		
		itemVil = new ItemBlock(vil);
		itemVil.setRegistryName(vil.getRegistryName());
		
		ForgeRegistries.BLOCKS.register(vil);
		ForgeRegistries.ITEMS.register(itemVil);
	}
}