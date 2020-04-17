package pokmon987.hammerandvil;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

public class HAVConfig {
	
	@Config(modid=HammerAndVil.MODID)
	public static class General {
		
		@Comment("Disabled by default. If enabled, when a recipe is crafted the vil will drop it as an entity, rather than"
				   + " keeping it in the vil")
			public static boolean dropOnCraft = false;
		
		@Comment("This will disable/enable the included recipes that turn concrete into concrete powder. It's recommended to disable them.")
		public static boolean includedRecipes = true;
	}
}