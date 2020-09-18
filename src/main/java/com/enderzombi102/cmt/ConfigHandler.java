package com.enderzombi102.cmt;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	
	public static ConfigHandler instance;
	
	public ConfigHandler() {
		ConfigHandler.instance = this;
	}
	
	@SubscribeEvent
    public void onConfigChangedEvent(OnConfigChangedEvent event) {
		LogHelper.info("modid: "+event.getModID());
        if (!event.getModID().equals(CustomMapsTools.MODID)) return;
        ConfigManager.sync(CustomMapsTools.MODID, Type.INSTANCE);
        // icon
        CustomMapsTools.CTweaker.updateIcon();
        CustomMapsTools.CTweaker.updateTitle();
    }
	
	@Config(modid = CustomMapsTools.MODID, type = Type.INSTANCE, name = "CustomMapsTools")
	public static class ConfigData {
		
		@RequiresWorldRestart
		@Name("integrated server MOTD")
		@Comment("leave blank for default MOTD")
		public static String intServMotd = "";
		
		@Name("Game window title")
		@Comment({"Windows Settings","","","the game window title is set to this value"})
		public static String windowTitle = "Minecraft 1.12.2";
		
		@Name("Game window icon")
		@Comment({"the game window icon to a png", "relative to the game instance folder", "leave blank for default icon"})
		public static String [] iconPath = {"",""};
		
		@Name("Minium game window dimensions")
		@Comment({"the game window will not be capable of being resized under this value","format: width,height"})
		public static String minWinDimension = "854, 480";

	}	
}
