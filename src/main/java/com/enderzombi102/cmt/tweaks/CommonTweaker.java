package com.enderzombi102.cmt.tweaks;

import net.minecraft.client.Minecraft;

public class CommonTweaker {
	
	protected Minecraft mcinstance = Minecraft.getMinecraft();
	
	public void update() {throw new NullPointerException("method update() should be overwritten");};
	
}
