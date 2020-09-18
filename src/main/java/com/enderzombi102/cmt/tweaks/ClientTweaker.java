package com.enderzombi102.cmt.tweaks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.nio.ByteBuffer;

import org.apache.commons.io.FileUtils;
import org.lwjgl.opengl.Display;

import com.enderzombi102.cmt.ConfigHandler;
import com.enderzombi102.cmt.LogHelper;
import com.enderzombi102.cmt.Utilities;

@SideOnly(Side.CLIENT)
public class ClientTweaker {
	
	private Minecraft mcinstance = Minecraft.getMinecraft();
	
	public ClientTweaker() {

		LogHelper.info(mcinstance.mcDataDir.getPath());
	}
	
	public void MotdSetter() {
		LogHelper.info("setting integrated server's MOTD!");
		if (mcinstance.getIntegratedServer() != null) {
	        mcinstance.getIntegratedServer().setMOTD(ConfigHandler.ConfigData.intServMotd);
		} else {
			LogHelper.info("we're running on dedicated server, skipping");
		}
	}
	
	public void updateTitle() {
		LogHelper.info("updating window title..");
		Display.setTitle(ConfigHandler.ConfigData.windowTitle);
		LogHelper.info("window title updated!");
		
	}
	
	public void updateIcon() {
		String icon16path, icon32path;
		if ( ConfigHandler.ConfigData.iconPath[0] != "" ) icon16path = ConfigHandler.ConfigData.iconPath[0];
		if ( ConfigHandler.ConfigData.iconPath[1] != "" ) icon32path = ConfigHandler.ConfigData.iconPath[1];
	}/*
		String path;
		
		
		// get the file
		File icons = {new File(), new File();
		// check if it exist
		mcinstance.updateDisplay();
		if (! icon.exists() ) {
			LogHelper.error("The specified icon file doesn't exist");
			return;
		} else {
			// it exists
			try {
				//read it
				ByteBuffer [] buf = { Utilities.readImageToBuffer(stream), Utilities.readImageToBuffer(stream) };
				LogHelper.info("setting icon..");
				// set the icon
				Display.setIcon(buf);
				LogHelper.info("setted icon!");
			} catch(Exception e) {
				LogHelper.error("Error while reading file: " + e);
			}
		}
	}*/
	
}
