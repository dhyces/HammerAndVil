package pokmon987.hammerandvil;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = HammerAndVil.MODID)
public class HAVConfig {
	
	@Config(modid = HammerAndVil.MODID)
	public static class General {
		
		@Comment("Disabled by default. If enabled, when a recipe is crafted the vil will drop it as an entity, rather than"
				+" keeping it in the vil.")
			public static boolean dropOnCraft = true;
		
		@RequiresMcRestart
		@Comment("This will disable/enable the included recipes that turn concrete into concrete powder. It's recommended "
				+"to disable them.")
			public static boolean includedRecipes = true;
		
		@Comment("Enables/disables placing into 3 slots. Disabling results in non-op player access to only one slot and "
				+ "will remove the current stack if right clicked with a different item. Enabled by default.")
			public static boolean multipleSlots = true;
		
		@Comment("Enables/disables the ability to stack items in the first slot. Enabled by default.")
			public static boolean multipleItemsInStack = true;
		
		/** This is disabled because it doesnt seem like something that should really be changed; its unnecessary*/
//		@Comment("Changing this changes the stack size of the first slot. Default value is 8. Number may be {1-64}.")
//			public static int stackSize = 8;
	}
	
	@SubscribeEvent
	public static void OnConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		System.out.println(event.getConfigID());
		if (event.getModID().equals(HammerAndVil.MODID)) {
			ConfigManager.sync(HammerAndVil.MODID, Config.Type.INSTANCE);
		}
	}
}