package com.enderzombi102.cmt.tweaks;

import net.minecraft.world.GameType;

public class ServerTweaker extends CommonTweaker {

	public void update() {
		
	}
	
	public void shareToLan(GameType mode, boolean allowCheats, int port) {
		mcinstance.getIntegratedServer().shareToLAN(mode, allowCheats);
	}
}
