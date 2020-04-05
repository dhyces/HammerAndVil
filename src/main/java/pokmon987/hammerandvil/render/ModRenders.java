package pokmon987.hammerandvil.render;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import pokmon987.hammerandvil.tileentity.TileVil;

public class ModRenders {
	 public static void init() {
		 ClientRegistry.bindTileEntitySpecialRenderer(TileVil.class, new RenderVil());
	 }
}