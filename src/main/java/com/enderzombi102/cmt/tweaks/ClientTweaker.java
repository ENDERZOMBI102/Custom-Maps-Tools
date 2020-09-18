package com.enderzombi102.cmt.tweaks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
		// TODO: finish this shit
		InputStream icon16 = null, icon32 = null;
		LogHelper.logger.info(ConfigHandler.ConfigData.iconPath);
		LogHelper.info("checking icons");
		if ( ConfigHandler.ConfigData.iconPath[0] != "" ) {
			try {
				icon16 = FileUtils.openInputStream( new File( ConfigHandler.ConfigData.iconPath[0] ) );
			} catch (IOException e) {
				LogHelper.error("the 16x16 icon path is wrong or the file doesn't exist, aborting.");
				return;
			}
		}
		else {
			try {
				icon16 = mcinstance.mcDefaultResourcePack.getInputStream( new ResourceLocation("icons/icon_16x16.png") );
			} catch (IOException e) {}
		}
		if ( ConfigHandler.ConfigData.iconPath[1] != "" ) {
			try {
				icon32 = FileUtils.openInputStream( new File( ConfigHandler.ConfigData.iconPath[1] ) );
			} catch (IOException e) {
				LogHelper.error("the 32x32 icon path is wrong or the file doesn't exist, aborting.");
				return;
			}
		}
		else {
			try {
				icon32 = mcinstance.mcDefaultResourcePack.getInputStream( new ResourceLocation("icons/icon_32x32.png") );
			} catch (IOException e) {}
		}
		// it exists
		try {
			//read it
			ByteBuffer [] buf = { Utilities.readImageToBuffer(icon16), Utilities.readImageToBuffer(icon32) };
			LogHelper.info("setting icon..");
			// set the icon
			Display.setIcon(buf);
			LogHelper.info("setted icon!");
		} catch(Exception e) {
			LogHelper.error("Error while reading file: " + e);
		}
	}
	
}
