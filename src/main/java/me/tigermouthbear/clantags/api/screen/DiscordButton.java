package me.tigermouthbear.clantags.api.screen;

import me.tigermouthbear.clantags.api.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author Tigermouthbear
 * @since 12/30/19
 */
class DiscordButton extends GuiButton {
	private static final ResourceLocation WHITE = new ResourceLocation("2b2tclantags", "white.png");

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
		mc.getTextureManager().bindTexture(WHITE);
		GlStateManager.color(0.33f, 0.33f, 1.0f, 1.0f);
		drawTexturedModalRect(x, y + mc.fontRenderer.FONT_HEIGHT, 0, 0, width, 1);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}
}
