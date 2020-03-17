package me.tigermouthbear.clantags.impl.mixin;

import me.tigermouthbear.clantags.api.ClanTagsApi;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * @author Tigermouthbear
 * 3/13/19
 */
@Mixin(GuiPlayerTabOverlay.class)
public class MixinGuiPlayerTabOverlay {
	/**
	 * Adds clantags to tab overlay
	 *
	 * @author Tigermouthbear
	 */
	@Overwrite
	public String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn) {
		return ClanTagsApi.handlePlayerTab(networkPlayerInfoIn);
	}
}
