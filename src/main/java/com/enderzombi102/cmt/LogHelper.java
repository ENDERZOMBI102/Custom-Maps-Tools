package com.enderzombi102.cmt;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class LogHelper {
	
	public static Logger logger;
	
	public LogHelper(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}
	
	public static void info(String text) {
		LogHelper.logger.info(text);
	}
	
	public static void debug(String text) {
		LogHelper.logger.debug(text);
	}
	
	public static void warn(String text) {
		LogHelper.logger.warn(text);
	}
	
	public static void error(String text) {
		LogHelper.logger.error(text);
	}
	
	public static void fatal(String text) {
		LogHelper.logger.fatal(text);
	}
}
