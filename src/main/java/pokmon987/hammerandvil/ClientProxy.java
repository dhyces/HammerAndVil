package pokmon987.hammerandvil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.obj.OBJModel.MaterialLibrary;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import pokmon987.hammerandvil.blocks.ModBlocks;
import pokmon987.hammerandvil.items.ModItems;
import pokmon987.hammerandvil.render.ModRenders;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public static Logger logger = LogManager.getLogger(HammerAndVil.NAME);
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		OBJLoader.INSTANCE.addDomain(HammerAndVil.MODID);
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ModRenders.init();
		logger.trace("HammerAndVil initialization completed");
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		registerModel(ModItems.itemHammer);
		registerModel(Item.getItemFromBlock(ModBlocks.vil));
		registerOBJ(new MaterialLibrary(), ModBlocks.vil.getRegistryName());
	}
	
	public static void registerOBJ(MaterialLibrary matLib, ResourceLocation resource) {
		new OBJModel(matLib, resource);
	}
	
	public static void registerModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}