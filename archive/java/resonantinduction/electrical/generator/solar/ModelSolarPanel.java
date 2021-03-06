package resonantinduction.electrical.generator.solar;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelSolarPanel extends ModelBase
{
	// fields
	ModelRenderer base;
	ModelRenderer neck;
	ModelRenderer panel;
	ModelRenderer brace;
	ModelRenderer braceLeft;
	ModelRenderer braceRight;

	public ModelSolarPanel()
	{
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-3.5F, 0F, -3.5F, 7, 1, 7);
		base.setRotationPoint(0F, 23F, 0F);
		base.setTextureSize(128, 128);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		neck = new ModelRenderer(this, 57, 17);
		neck.addBox(-3F, 0F, -2.5F, 6, 2, 5);
		neck.setRotationPoint(0F, 21F, 0F);
		neck.setTextureSize(128, 128);
		neck.mirror = true;
		setRotation(neck, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 0, 31);
		panel.addBox(-7F, 0F, -7F, 14, 3, 14);
		panel.setRotationPoint(0F, 15F, 0F);
		panel.setTextureSize(128, 128);
		panel.mirror = true;
		setRotation(panel, 0F, 0F, 0F);
		brace = new ModelRenderer(this, 9, 16);
		brace.addBox(-8F, 0F, -3F, 16, 2, 6);
		brace.setRotationPoint(0F, 19F, 0F);
		brace.setTextureSize(128, 128);
		brace.mirror = true;
		setRotation(brace, 0F, 0F, 0F);
		braceLeft = new ModelRenderer(this, 57, 0);
		braceLeft.addBox(7F, 0F, -3F, 1, 3, 6);
		braceLeft.setRotationPoint(0F, 16F, 0F);
		braceLeft.setTextureSize(128, 128);
		braceLeft.mirror = true;
		setRotation(braceLeft, 0F, 0F, 0F);
		braceRight = new ModelRenderer(this, 39, 0);
		braceRight.addBox(-8F, 0F, -3F, 1, 3, 6);
		braceRight.setRotationPoint(0F, 16F, 0F);
		braceRight.setTextureSize(128, 128);
		braceRight.mirror = true;
		setRotation(braceRight, 0F, 0F, 0F);
	}

	public void render(float f5)
	{
		base.render(f5);
		neck.render(f5);
		panel.render(f5);
		brace.render(f5);
		braceLeft.render(f5);
		braceRight.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
