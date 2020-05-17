package pokmon987.hammerandvil.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelVil extends ModelBase{
	
	final ModelRenderer box;
	public ModelVil() {
		box = new ModelRenderer(this);
		box.addBox(0, 0, 0, 16, 16, 16);
	}
	
	public void render(double x, double y, double z) {
		box.offsetX = (float) x;
		box.offsetY = (float) y;
		box.offsetZ = (float) z;
		box.render(0.0625F);
		
	}
}