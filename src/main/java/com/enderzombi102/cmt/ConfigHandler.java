package com.enderzombi102.cmt;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
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
        CustomMapsTools.CTweaker.update();
        CustomMapsTools.STweaker.update();
    }
	
	@Config(modid = CustomMapsTools.MODID, type = Type.INSTANCE, name = "CustomMapsTools")
	public static class ConfigData {
		
		@Name("Integrated server MOTD")
		@Comment("leave blank for default MOTD")
		public static String intServMotd = "";
		
		@Name("Auto share to lan")
		@Comment("automatically share world to lan")
		public static boolean autoServerShare = false;
		
		@Name("Game window title")
		@Comment("the game window title is set to this value")
		public static String windowTitle = "Minecraft 1.12.2";
		
		@Name("Game window icon")
		@Comment({"set the game window icon to a png", "relative to the game instance folder", "leave blank for default icon"})
		public static String iconPath16 = "";
		
		@Name("Game taskbar icon")
		@Comment({"set the game taskbar icon to a png", "relative to the game instance folder", "leave blank for default icon"})
		public static String iconPath32 = "";
		
		@Name("Minimum game window dimensions")
		@Comment({"the game window will not be capable of resizing under this value","format: width,height"})
		public static String minWinDimension = "854, 480";

	}	
}
