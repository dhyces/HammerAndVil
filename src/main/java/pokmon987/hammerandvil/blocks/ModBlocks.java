package pokmon987.hammerandvil.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pokmon987.hammerandvil.HammerAndVil;

@EventBusSubscriber(modid = HammerAndVil.MODID)
public class ModBlocks {
	public static BlockVil vil = new BlockVil();
	
	@SubscribeEvent
	public static void onBlockRegistryEvent(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(vil);
	}
}