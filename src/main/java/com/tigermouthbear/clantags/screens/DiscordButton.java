package com.tigermouthbear.clantags.screens;

import com.tigermouthbear.clantags.Globals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class DiscordButton extends GuiButton implements Globals
{
	private String id;

	public DiscordButton(int x, int y, String id)
	{
		//only should be 1 discord button per clanscreen
		super(69, x, y, mc.fontRenderer.getStringWidth("https://discord.gg/"), mc.fontRenderer.FONT_HEIGHT+2, "");
		this.id = id;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		drawString(mc.fontRenderer, "https://discord.gg/" + id, x, y, Integer.parseInt("5555FF", 16));
	}
}
