package pokmon987.hammerandvil.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import pokmon987.hammerandvil.HAVConfig;
import pokmon987.hammerandvil.HammerAndVil;
import pokmon987.hammerandvil.items.ModItems;

@Mod.EventBusSubscriber(modid = HammerAndVil.MODID)
public class ModRecipes {
	
	ShapedOreRecipe vil = new ShapedOreRecipe(new ResourceLocation(HammerAndVil.MODID + "vil"), new ItemStack(ModItems.itemVil));
	ShapedOreRecipe hammer = new ShapedOreRecipe(new ResourceLocation(HammerAndVil.MODID + "hammer"), new ItemStack(ModItems.itemHammer));
	
	public static void init() {
		if (HAVConfig.General.includedRecipes) {
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":whiteConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.WHITE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.WHITE.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":orangeConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.ORANGE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.ORANGE.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":magentaConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.MAGENTA.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.MAGENTA.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":lightBlueConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":yellowConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.YELLOW.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.YELLOW.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":limeConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.LIME.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIME.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":pinkConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.PINK.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PINK.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":grayConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.GRAY.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GRAY.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":lightGrayConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.SILVER.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.SILVER.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":cyanConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.CYAN.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":purpleConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.PURPLE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PURPLE.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":blueConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLUE.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLUE.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":brownConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BROWN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BROWN.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":greenConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.GREEN.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GREEN.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":redConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.RED.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.RED.getMetadata()), 3, ItemStack.EMPTY);
			VilRecipes.addVilRecipe(HammerAndVil.MODID + ":blackConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, EnumDyeColor.BLACK.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLACK.getMetadata()), 3, ItemStack.EMPTY);
		}
	}
	
	@SubscribeEvent
	public void registerTableRecipes(RegistryEvent.Register<IRecipe> event) {
		IForgeRegistry<IRecipe> register = event.getRegistry();
		
		register.registerAll(vil, hammer);
	}
}