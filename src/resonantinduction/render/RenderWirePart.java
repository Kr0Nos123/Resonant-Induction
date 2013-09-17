package resonantinduction.render;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import codechicken.multipart.TMultiPart;
import resonantinduction.ResonantInduction;
import resonantinduction.model.ModelInsulation;
import resonantinduction.model.ModelWire;
import resonantinduction.wire.EnumWireMaterial;
import resonantinduction.wire.multipart.PartWire;
import universalelectricity.core.vector.Vector3;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * TODO: Use ISBRH.
 * 
 * @author Calclavia
 * 
 */
@SideOnly(Side.CLIENT)
public class RenderWirePart
{
	private static final ResourceLocation WIRE_TEXTURE = new ResourceLocation(ResonantInduction.DOMAIN, ResonantInduction.MODEL_TEXTURE_DIRECTORY + "wire.png");
	private static final ResourceLocation INSULATION_TEXTURE = new ResourceLocation(ResonantInduction.DOMAIN, ResonantInduction.MODEL_TEXTURE_DIRECTORY + "insulation.png");
	public static final ModelWire WIRE_MODEL = new ModelWire();
	public static final ModelInsulation INSULATION_MODEL = new ModelInsulation();

	public void renderModelAt(PartWire part, double x, double y, double z, float f)
	{
		if (part != null)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glScalef(1, -1, -1);

			EnumWireMaterial material = part.getMaterial();
			// Texture file
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(WIRE_TEXTURE);
			GL11.glColor4d(material.color.x, material.color.y, material.color.z, 1);

			part.adjacentConnections = null;
			TileEntity[] adjacentConnections = part.getAdjacentConnections();

			if (adjacentConnections != null)
			{
				if (adjacentConnections[0] != null)
				{
					WIRE_MODEL.renderBottom();
				}

				if (adjacentConnections[1] != null)
				{
					WIRE_MODEL.renderTop();
				}

				if (adjacentConnections[2] != null)
				{
					WIRE_MODEL.renderBack();
				}

				if (adjacentConnections[3] != null)
				{
					WIRE_MODEL.renderFront();
				}

				if (adjacentConnections[4] != null)
				{
					WIRE_MODEL.renderLeft();
				}

				if (adjacentConnections[5] != null)
				{
					WIRE_MODEL.renderRight();
				}
			}

			WIRE_MODEL.renderMiddle();

			if (part.isInsulated)
			{
				// Texture file
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(INSULATION_TEXTURE);
				Vector3 insulationColor = ResonantInduction.DYE_COLORS[part.dyeID];
				GL11.glColor4d(insulationColor.x, insulationColor.y, insulationColor.z, 1);

				if (adjacentConnections != null)
				{
					if (adjacentConnections[0] != null)
					{
						INSULATION_MODEL.renderBottom(0.0625f);
					}

					if (adjacentConnections[1] != null)
					{
						INSULATION_MODEL.renderTop(0.0625f);
					}

					if (adjacentConnections[2] != null)
					{
						INSULATION_MODEL.renderBack(0.0625f);
					}

					if (adjacentConnections[3] != null)
					{
						INSULATION_MODEL.renderFront(0.0625f);
					}

					if (adjacentConnections[4] != null)
					{
						INSULATION_MODEL.renderLeft(0.0625f);
					}

					if (adjacentConnections[5] != null)
					{
						INSULATION_MODEL.renderRight(0.0625f);
					}
				}

				INSULATION_MODEL.renderMiddle(0.0625f);
			}

			GL11.glPopMatrix();
		}
	}
	
	public void renderWirePartAt(TMultiPart part, double var2, double var4, double var6, float var8)
	{
		this.renderModelAt((PartWire) part, var2, var4, var6, var8);
	}
}