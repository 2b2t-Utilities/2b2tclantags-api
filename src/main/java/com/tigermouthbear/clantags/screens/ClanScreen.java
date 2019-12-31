package com.tigermouthbear.clantags.screens;

import com.tigermouthbear.clantags.Utils;
import com.tigermouthbear.clantags.data.Clan;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class ClanScreen extends GuiScreen
{
	private Clan clan;

	public ClanScreen(Clan clan)
	{
		this.clan = clan;
	}

	@Override
	public void initGui()
	{
		this.initGui();

		buttonList.add(new DiscordButton(0, 0, clan.getDiscord()));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		drawCenteredScaledString(clan.getFullName(), width/2, mc.fontRenderer.FONT_HEIGHT/2, 2.0d, Utils.colors.get(clan.getColor()));
		mc.fontRenderer.drawSplitString(clan.getDescription(), width/3, (mc.fontRenderer.FONT_HEIGHT/2 + 10) * 2, width/3, Integer.parseInt("FFFFFF", 16));
	}

	private void drawCenteredScaledString(String text, int x, int y, double scale, String color)
	{
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, 1);
		drawCenteredString(mc.fontRenderer, text, (int)(x/scale), (int)(y/scale), Integer.parseInt(color, 16));
		GlStateManager.popMatrix();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button instanceof DiscordButton) Utils.openLink("https://discord.gg/" + button.id);

		super.actionPerformed(button);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
