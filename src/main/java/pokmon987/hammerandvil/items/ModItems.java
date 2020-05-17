package pokmon987.hammerandvil.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.blocks.ModBlocks;

@EventBusSubscriber(modid = HammerAndVil.MODID)
public class ModItems {
	
	public static ItemHammer itemHammer = new ItemHammer();
	public static ItemBlock itemVil = new ItemBlock(ModBlocks.vil);

	@SubscribeEvent
	public static void onItemRegisteredEvent(RegistryEvent.Register<Item> event) {
		itemVil.setRegistryName(ModBlocks.vil.getRegistryName());
		event.getRegistry().registerAll(itemHammer, itemVil);
	}
}