package pokmon987.hammerandvil.tileentity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pokmon987.hammerandvil.HammerAndVil;

public class ModTiles {
	
	public static void init() {
		GameRegistry.registerTileEntity(TileVil.class, new ResourceLocation(HammerAndVil.MODID + "vil"));
	}
}
