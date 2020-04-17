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
		
		
		//GlStateManager.pushMatrix();
		//Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(te.getWorld(), modelIn, blockStateIn, te.getPos(), buffer, checkSides);
		ItemStack stack = te.getInventory().getStackInSlot(0);
		if (!stack.isEmpty()) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.pushMatrix();
			GlStateManager.translate(x+translateX, y+0.77D, z+translateZ);
			GlStateManager.rotate(90, 1, 0, 0);
			GlStateManager.rotate(rotY, 0, 0, 1);
			float scale = stack.getItem() instanceof ItemBlock ? 0.55F : 0.45F;
			GlStateManager.scale(scale, scale, scale);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);
			if (stack.getCount() > 1) {
				for (int i = 1; i < stack.getCount(); i++) {
					GlStateManager.translate(0, 0, -0.07);
					Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);
				}
			}
			GlStateManager.popMatrix();
		}
	}
}