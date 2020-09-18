package com.enderzombi102.cmt;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;


import com.enderzombi102.cmt.tweaks.*;

@Mod(modid = CustomMapsTools.MODID, name = CustomMapsTools.NAME, version = CustomMapsTools.VERSION)
public class CustomMapsTools
{
    // forge mod stuff
	public static final String MODID = "cmt";
    public static final String NAME = "Custom Maps Tools";
    public static final String VERSION = "1.0";
    @Mod.Instance(MODID)
    public static CustomMapsTools instance;
    // mod's stuff
    public static ClientTweaker CTweaker;
    public static ServerTweaker STweaker;
    @SuppressWarnings("unused")
	private static LogHelper logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // registering stuff
    	MinecraftForge.EVENT_BUS.register(instance);
    	MinecraftForge.EVENT_BUS.register( new ConfigHandler() );
        // creating log object
        logger = new LogHelper(event);
        CustomMapsTools.CTweaker = new ClientTweaker();
        CustomMapsTools.STweaker = new ServerTweaker();
        CTweaker.updateIcon();
        CTweaker.updateTitle();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        LogHelper.info("DIRT BLOCK >> " + Blocks.DIRT.getRegistryName());
    }
}
