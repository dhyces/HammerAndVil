package pokmon987.hammerandvil.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokmon987.hammerandvil.tileentity.TileVil;

@SideOnly(Side.CLIENT)
public class RenderVil extends TileEntitySpecialRenderer<TileVil> {
	
	@Override
	public void render(TileVil te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		int dir = te.getBlockMetadata();
		int rotY = 0;
		double translateX = 0.5D;
		double translateZ = 0.4D;
		
		switch (dir) {
			case 0:
				break;
			case 1:
				rotY = 90;
				translateX = 0.6D;
				translateZ = 0.5D;
				break;
			case 2:
				rotY = 180;
				translateX = 0.5D;
				translateZ = 0.6D;
				break;
			case 3:
				rotY = 270;
				translateX = 0.4D;
				translateZ = 0.5D;
				break;
		} 
		
		
		ItemStack stack = getItem(te, 0);
		
		//GlStateManager.pushMatrix();
		//model.render(x, y, z);
		//GlStateManager.popMatrix();
		if (!stack.isEmpty()) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.pushMatrix();
			GlStateManager.translate(x+translateX, y+0.77D, z+translateZ);
			GlStateManager.rotate(90, 1, 0, 0);
			GlStateManager.rotate(rotY, 0, 0, 1);
			if (getItem(te, 1).isEmpty()) {
				float scale = stack.getItem() instanceof ItemBlock ? 0.55F : 0.45F;
				GlStateManager.scale(scale, scale, scale);
				Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);
				if (stack.getCount() > 1) {
					for (int i = 1; i < stack.getCount(); i++) {
						GlStateManager.translate(0D, 0D, -0.07D);
						Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);
					}
				}
			} else {
				for (int i = 0; i < te.getInventory().getSlots(); i++) {
					float scale = getItem(te, i).getItem() instanceof ItemBlock ? 0.35F : 0.25F;
					GlStateManager.scale(scale, scale, scale);
					double translationSides = 0;
					double translationVertical = 0;
					
					if (i == 0) {
						translationSides = getItem(te, i).getItem() instanceof ItemBlock ? 0.45D : 0.55D;
					}
					if (i == 1) {
						translationSides = getItem(te, i).getItem() instanceof ItemBlock ? -0.85D : -1.05D;
					}
					if (i == 2) {
						translationSides = getItem(te, i).getItem() instanceof ItemBlock ? 0.55D : 0.45D;
						translationVertical = -0.7D;
					}
					GlStateManager.translate(translationSides, translationVertical, 0);
					Minecraft.getMinecraft().getRenderItem().renderItem(getItem(te, i), TransformType.FIXED);
					float scaleBack = getItem(te, i).getItem() instanceof ItemBlock ? 2.8571428571F : 4F;
					GlStateManager.scale(scaleBack, scaleBack, scaleBack);
				}
//				float scale = stack.getItem() instanceof ItemBlock ? 0.35F : 0.25F;
//				float scale2 = getItem(te, 1).getItem() instanceof ItemBlock ? 0.35F : 0.25F;
//				float scale3 = getItem(te, 2).getItem() instanceof ItemBlock ? 0.35F : 0.25F;
//				GlStateManager.scale(scale, scale, scale);
//				//double translation = 
//				GlStateManager.translate(0.55, 0, 0);
//				Minecraft.getMinecraft().getRenderItem().renderItem(getItem(te, 0), TransformType.FIXED);
//				GlStateManager.translate(-1.05, 0, 0);
//				Minecraft.getMinecraft().getRenderItem().renderItem(getItem(te, 1),  TransformType.FIXED);
			}
			GlStateManager.popMatrix();
		}
	}
	
	public ItemStack getItem(TileVil te, int slot) {
		return te.getInventory().getStackInSlot(slot);
	}
}