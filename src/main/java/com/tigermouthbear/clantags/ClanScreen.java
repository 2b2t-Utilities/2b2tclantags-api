package com.tigermouthbear.clantags;

import com.tigermouthbear.clantags.data.Clan;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

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
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
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
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
