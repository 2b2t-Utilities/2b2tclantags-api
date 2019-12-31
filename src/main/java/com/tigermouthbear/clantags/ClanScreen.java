package com.tigermouthbear.clantags;

import com.tigermouthbear.clantags.data.Clan;
import net.minecraft.client.gui.GuiScreen;

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
		drawCenteredString(mc.fontRenderer, clan.getFullName(), width/2, 2, Integer.parseInt("FFFFFF", 16));
	}
}
