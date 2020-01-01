package com.tigermouthbear.clantags.screens;

import com.tigermouthbear.clantags.ClanTags;
import com.tigermouthbear.clantags.Globals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class DiscordButton extends GuiButton implements Globals
{
	private static final ResourceLocation WHITE = new ResourceLocation(ClanTags.MODID, "white.png");

	private String discord;

	public DiscordButton(int x, int y, String discord)
	{
		//only should be 1 discord button per clanscreen
		super(69, x, y, mc.fontRenderer.getStringWidth("discord.gg/" + discord), mc.fontRenderer.FONT_HEIGHT+2, "");
		this.discord = discord;
	}

	public String getDiscord()
	{
		return discord;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		drawString(mc.fontRenderer, "discord.gg/" + discord, x, y, Integer.parseInt("5555FF", 16));
		mc.getTextureManager().bindTexture(WHITE);
		GlStateManager.color(0.33f, 0.33f, 1.0f, 1.0f);
		drawTexturedModalRect(x, y + mc.fontRenderer.FONT_HEIGHT, 0, 0, width, 1);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}
}
