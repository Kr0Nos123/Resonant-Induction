package resonantinduction;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Calclavia
 * 
 */
@Mod(modid = ResonantInduction.ID, name = ResonantInduction.NAME, version = ResonantInduction.VERSION)
public class ResonantInduction
{
	/**
	 * Mod Information
	 */
	public static final String ID = "ResonantInduction";
	public static final String NAME = "Resonant Induction";

	public static final String MAJOR_VERSION = "@MAJOR@";
	public static final String MINOR_VERSION = "@MINOR@";
	public static final String REVISION_VERSION = "@REVIS@";
	public static final String BUILD_VERSION = "@BUILD@";
	public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION;

	@Instance(ID)
	public static ResonantInduction INSTNACE;

	@Mod.Metadata(ID)
	public static ModMetadata metadata;

	/**
	 * Directory Information
	 */
	public static final String DOMAIN = "resonantinduction";
	public static final String PREFIX = DOMAIN + ":";
	public static final String DIRECTORY = "/assets/" + DOMAIN + "/";
	public static final String TEXTURE_DIRECTORY = "textures/";
	public static final String GUI_DIRECTORY = TEXTURE_DIRECTORY + "/gui";
	public static final String BLOCK_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "/blocks";
	public static final String ITEM_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "/items";
	public static final String MODEL_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "/models";

	public static final String LANGUAGE_DIRECTORY = TEXTURE_DIRECTORY + "/models";
	public static final String[] LANGUAGES = new String[] { "en_US" };

	/**
	 * Settings
	 */
	public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(), NAME + ".cfg"));

	/** Block ID by Jyzarc */
	private static final int BLOCK_ID_PREFIX = 3200;
	/** Item ID by Horfius */
	private static final int ITEM_ID_PREFIX = 20150;

	private static int NEXT_BLOCK_ID = BLOCK_ID_PREFIX;
	private static int NEXT_ITEM_ID = ITEM_ID_PREFIX;

	public static int getNextBlockID()
	{
		return NEXT_BLOCK_ID++;
	}

	public static int getNextItemID()
	{
		return NEXT_ITEM_ID++;
	}

	/**
	 * Blocks and Items
	 */
	public static Block blockTesla;

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{

	}

	@EventHandler
	public void init(FMLInitializationEvent evt)
	{

	}

	@EventHandler
	public void preInit(FMLPostInitializationEvent evt)
	{

	}
}
