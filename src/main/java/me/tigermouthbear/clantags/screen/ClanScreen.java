package me.tigermouthbear.clantags.screen;

import me.tigermouthbear.clantags.Utils;
import me.tigermouthbear.clantags.data.Clan;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;

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
		super.initGui();

		buttonList.add(new DiscordButton(width/2 - mc.fontRenderer.getStringWidth("discord.gg/" + clan.getDiscord())/2, mc.fontRenderer.FONT_HEIGHT*2+4, clan.getDiscord()));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		drawCenteredScaledString(clan.getFullName(), width/2, mc.fontRenderer.FONT_HEIGHT/2, 2.0d, Utils.colors.get(clan.getColor()));
		mc.fontRenderer.drawSplitString(clan.getDescription(), width/3, (mc.fontRenderer.FONT_HEIGHT/2 + 15) * 2, width/3, Integer.parseInt("FFFFFF", 16));

		//long thing, theres probably something better
		int height = (int) (((mc.fontRenderer.FONT_HEIGHT/2 + 15) * 2) + 20 + Math.ceil(mc.fontRenderer.getStringWidth(clan.getDescription()) / (width/3))*(mc.fontRenderer.FONT_HEIGHT + 1));
		drawString(mc.fontRenderer, "Allies: ", width/3, height, Integer.parseInt(Utils.colors.get("green"), 16));
		mc.fontRenderer.drawSplitString(clan.allies, width/3 + mc.fontRenderer.getStringWidth("Allies: "), height, width/3, Integer.parseInt("FFFFFF", 16));

		height += (Math.ceil(mc.fontRenderer.getStringWidth("Allies: " + clan.allies) / (width/3)) + 1)*(mc.fontRenderer.FONT_HEIGHT + 1);
		drawString(mc.fontRenderer, "Enemies: ", width/3, height, Integer.parseInt(Utils.colors.get("red"), 16));
		mc.fontRenderer.drawSplitString(clan.enemies, width/3 + mc.fontRenderer.getStringWidth("Enemies: "), height, width/3, Integer.parseInt("FFFFFF", 16));

		super.drawScreen(mouseX, mouseY, partialTicks);
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
		if(button instanceof DiscordButton) Utils.openLink("https://discord.gg/" + ((DiscordButton) button).getDiscord());

		super.actionPerformed(button);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
