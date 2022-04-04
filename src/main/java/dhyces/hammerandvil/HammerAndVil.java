package dhyces.hammerandvil;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HammerAndVil.MODID)
public class HammerAndVil {

	public static final String MODID = "hammerandvil";
	
	public HammerAndVil() {
		var modbus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modbus.addListener(this::registerRenderers);
	}
	
	private void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		
	}
}
