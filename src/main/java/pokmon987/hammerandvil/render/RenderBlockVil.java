package pokmon987.hammerandvil.render;

import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.util.ResourceLocation;

public class RenderBlockVil extends ModelBlock{

	public RenderBlockVil(ResourceLocation parentLocationIn, List<BlockPart> elementsIn, Map<String, String> texturesIn,
			boolean ambientOcclusionIn, boolean gui3dIn, ItemCameraTransforms cameraTransformsIn,
			List<ItemOverride> overridesIn) {
		super(parentLocationIn, elementsIn, texturesIn, ambientOcclusionIn, gui3dIn, cameraTransformsIn, overridesIn);
		// TODO Auto-generated constructor stub
	}
	
}