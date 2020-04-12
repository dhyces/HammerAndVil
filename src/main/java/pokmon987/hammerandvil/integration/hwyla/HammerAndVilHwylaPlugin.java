package pokmon987.hammerandvil.integration.hwyla;

import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.blocks.BlockVil;

@WailaPlugin
public class HammerAndVilHwylaPlugin implements IWailaPlugin {

	@Override
	public void register(IWailaRegistrar registrar) {
		HUDHandlerVil dataProvider = new HUDHandlerVil();
		
		registrar.registerBodyProvider(dataProvider, BlockVil.class);
		registrar.registerNBTProvider(dataProvider, BlockVil.class);
		registrar.addConfig(HammerAndVil.NAME, HammerAndVil.MODID + ".vil", true);
	}
	
}