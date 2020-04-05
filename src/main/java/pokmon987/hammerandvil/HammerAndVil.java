package pokmon987.hammerandvil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HammerAndVil.MODID, name = HammerAndVil.NAME, version = HammerAndVil.VERSION)
public class HammerAndVil {
	public static final String MODID = "hammerandvil";
	public static final String NAME = "HammerAndVil";
	public static final String VERSION = "1.0.0";
	
	@SidedProxy(clientSide = "pokmon987.hammerandvil.ClientProxy", serverSide = "pokmon987.hammerandvil.ServerProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static HammerAndVil instance;
	
	public static Logger logger = LogManager.getLogger(NAME);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}