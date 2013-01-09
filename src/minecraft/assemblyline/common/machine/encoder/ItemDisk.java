package assemblyline.common.machine.encoder;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import assemblyline.common.AssemblyLine;
import assemblyline.common.TabAssemblyLine;

public class ItemDisk extends Item
{
	public ItemDisk(int id)
	{
		super(id);
		this.setItemName("disk");
		this.setIconIndex(0);
		this.setCreativeTab(TabAssemblyLine.INSTANCE);
		this.setHasSubtypes(true);
		this.setTextureFile(AssemblyLine.ITEM_TEXTURE_PATH);
	}

	@Override
	public int getItemStackLimit()
	{
		return 1;
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		List<String> commands = getCommands(itemStack);

		if (commands.size() > 0)
		{
			list.add(commands.size() + " command(s)");
		}
		else
		{
			list.add("No commands");
		}
	}

	/**
	 * Saves the list of items to filter out inside.
	 */
	public static void setCommands(ItemStack itemStack, ArrayList<String> commands)
	{
		if (itemStack.getTagCompound() == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		NBTTagList nbt = new NBTTagList();

		for (int i = 0; i < commands.size(); ++i)
		{
			if (commands.get(i) != null)
			{
				NBTTagCompound newCompound = new NBTTagCompound();
				newCompound.setString("command", commands.get(i));
				nbt.appendTag(newCompound);
			}
		}

		itemStack.getTagCompound().setTag("Commands", nbt);
	}

	public static ArrayList<String> getCommands(ItemStack itemStack)
	{
		ArrayList<String> commands = new ArrayList<String>();

		if (itemStack.getTagCompound() == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		NBTTagCompound nbt = itemStack.getTagCompound();
		NBTTagList tagList = nbt.getTagList("Commands");

		for (int i = 0; i < tagList.tagCount(); ++i)
		{
			NBTTagCompound curTag = (NBTTagCompound) tagList.tagAt(i);
			String cmd = curTag.getString("command");
			commands.add(cmd);
		}

		return commands;
	}
}
