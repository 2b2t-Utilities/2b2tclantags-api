package dev.tigr.clantags.api.screen;

import dev.tigr.clantags.api.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

/**
 * @author Tigermouthbear
 * @since 12/30/19
 */
class DiscordButton extends GuiButton {
	private String discord;

	DiscordButton(Minecraft mc, int x, int y, String discord) {
		//only should be 1 discord button per clanscreen
		super(69, x, y, mc.fontRenderer.getStringWidth("discord.gg/" + discord), mc.fontRenderer.FONT_HEIGHT + 2, "");
		this.discord = discord;
	}

	String getDiscord() {
		return discord;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		drawString(mc.fontRenderer, "discord.gg/" + discord, x, y, Integer.parseInt(Utils.colors.get("blue"), 16));
		GlStateManager.color(1, 1, 1, 1);
		Gui.drawRect(x, y + mc.fontRenderer.FONT_HEIGHT, x + width, y + 1, 5526783);
		drawTexturedModalRect(x, y + mc.fontRenderer.FONT_HEIGHT, 0, 0, width, 1);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}
}
