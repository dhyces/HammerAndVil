package pokmon987.hammerandvil.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import pokmon987.hammerandvil.HammerAndVil;

public class ModBlocks {
	public static BlockVil vil;
	public static ItemBlock itemVil;
	
	public static void init() {
		vil = (BlockVil)(new BlockVil().setRegistryName("vil"));
		vil.setUnlocalizedName(HammerAndVil.MODID + ".vil");
		
		itemVil = new ItemBlock(vil);
		itemVil.setRegistryName(vil.getRegistryName());
		
		ForgeRegistries.BLOCKS.register(vil);
		ForgeRegistries.ITEMS.register(itemVil);
	}
}