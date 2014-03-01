package resonantinduction.archaic.fluid.gutter;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import resonantinduction.core.fluid.IPressurizedNode;
import resonantinduction.core.fluid.TilePressurizedNode;
import universalelectricity.api.vector.Vector3;
import calclavia.lib.utility.FluidUtility;
import calclavia.lib.utility.WorldUtility;

/**
 * The gutter, used for fluid transfer.
 * 
 * @author Calclavia
 * 
 */
public class TileGutter extends TilePressurizedNode implements IPressurizedNode
{
	@Override
	public void updateEntity()
	{
		super.updateEntity();

		// TODO: Packet before doing.
		if (!this.worldObj.isRemote)
			sendTankUpdate();
	}

	@Override
	public void refresh()
	{
		/**
		 * Drain block above if it is a fluid.
		 */
		FluidStack drain = FluidUtility.drainBlock(worldObj, new Vector3(this).translate(0, 1, 0), true);

		if (drain != null)
			fill(ForgeDirection.UP, drain, true);

		super.refresh();
	}

	@Override
	public void validateConnectionSide(TileEntity tileEntity, ForgeDirection side)
	{
		if (!this.worldObj.isRemote)
		{
			if (tileEntity instanceof IFluidHandler)
			{
				if (tileEntity instanceof TileGutter)
				{
					getNetwork().merge(((TileGutter) tileEntity).getNetwork());
				}

				renderSides = WorldUtility.setEnableSide(renderSides, side, true);
				connectedBlocks[side.ordinal()] = tileEntity;
			}
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if (!resource.getFluid().isGaseous())
		{
			return super.fill(from, resource, doFill);
		}

		return 0;
	}

	@Override
	public int getPressure(ForgeDirection dir)
	{
		if (dir == ForgeDirection.UP)
			return -3;

		if (dir == ForgeDirection.DOWN)
			return +3;

		return pressure;
	}

	@Override
	public int getMaxFlowRate()
	{
		return 20;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return from != ForgeDirection.UP && !fluid.isGaseous();
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return from != ForgeDirection.UP && !fluid.isGaseous();
	}
}