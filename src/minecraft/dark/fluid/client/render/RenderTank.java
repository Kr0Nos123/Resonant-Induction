package dark.fluid.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

import org.lwjgl.opengl.GL11;

import dark.fluid.client.model.ModelTankSide;
import dark.fluid.common.FluidMech;
import dark.fluid.common.machines.TileEntityTank;
import dark.hydraulic.api.ColorCode;
import dark.hydraulic.helpers.LiquidRenderer;
import dark.library.DarkMain;

public class RenderTank extends TileEntitySpecialRenderer
{
	private ModelTankSide model;

	public RenderTank()
	{
		model = new ModelTankSide();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float var8)
	{
		if (tileEntity instanceof TileEntityTank)
		{
			TileEntityTank tileEntityTank = ((TileEntityTank) tileEntity);
			ILiquidTank tank = tileEntityTank.getTank();
			LiquidStack liquid = tank.getLiquid();

			int[] render = ((TileEntityTank) tileEntity).renderConnection;

			ColorCode color = tileEntityTank.getColor();
			if (liquid != null && liquid.amount > 0)
			{

				int[] displayList = LiquidRenderer.getLiquidDisplayLists(liquid, tileEntity.worldObj, false);
				if (displayList != null)
				{
					GL11.glPushMatrix();
					GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
					GL11.glEnable(GL11.GL_CULL_FACE);
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					bindTextureByName(LiquidRenderer.getLiquidSheet(liquid));

					GL11.glTranslatef((float) x, (float) y, (float) z);
					GL11.glScalef(1.01F, 1F, 1.01F);
					GL11.glCallList(displayList[(int) ((float) Math.min(liquid.amount, tank.getCapacity()) / (float) (tank.getCapacity()) * (LiquidRenderer.DISPLAY_STAGES - 1))]);

					GL11.glPopAttrib();
					GL11.glPopMatrix();
				}
			}
			bindTextureByName(DarkMain.TEXTURE_DIRECTORY + "CobbleBack.png");

			boolean bot = render[1] == 2;
			boolean top = render[0] == 2;
			boolean north = render[2] == 2;
			boolean south = render[3] == 2;
			boolean east = render[5] == 2;
			boolean west = render[4] == 2;
			for (int i = 0; i < 4; i++)
			{
				ForgeDirection dir = ForgeDirection.getOrientation(i + 2);
				if (render[dir.getOpposite().ordinal()] != 2)
				{
					GL11.glPushMatrix();
					GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
					GL11.glScalef(1.0F, -1F, -1F);
					boolean left = false;
					boolean right = false;
					switch (dir)
					{
						case NORTH:
							GL11.glRotatef(180f, 0f, 1f, 0f);
							left = west;
							right = east;
							break;
						case SOUTH:
							GL11.glRotatef(0f, 0f, 1f, 0f);
							left = east;
							right = west;
							break;
						case WEST:
							GL11.glRotatef(90f, 0f, 1f, 0f);
							left = south;
							right = north;
							break;
						case EAST:
							GL11.glRotatef(270f, 0f, 1f, 0f);
							left = north;
							right = south;
							break;
					}

					model.render(0.0625F, left, right, top, bot);

					GL11.glPopMatrix();
				}
			}
		}
	}
}