package pokmon987.hammerandvil.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.blocks.ModBlocks;
import pokmon987.hammerandvil.items.ModItems;
import pokmon987.hammerandvil.recipes.VilRecipes;

@Mod.EventBusSubscriber(modid = HammerAndVil.MODID)
public class ModRecipes {
	
	ShapedOreRecipe vil = new ShapedOreRecipe(new ResourceLocation(HammerAndVil.MODID + "vil"), new ItemStack(ModBlocks.itemVil));
	ShapedOreRecipe hammer = new ShapedOreRecipe(new ResourceLocation(HammerAndVil.MODID + "hammer"), new ItemStack(ModItems.itemHammer));
	
	public static void init() {
		VilRecipes.registerRecipes();
		
	}
	
	@SubscribeEvent
	public void registerTableRecipes(RegistryEvent.Register<IRecipe> event) {
		IForgeRegistry<IRecipe> register = event.getRegistry();
		
		register.registerAll(vil, hammer);
	}
}