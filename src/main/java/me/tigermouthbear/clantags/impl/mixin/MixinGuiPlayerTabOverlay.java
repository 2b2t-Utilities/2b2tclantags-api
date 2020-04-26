package me.tigermouthbear.clantags.impl.mixin;

import me.tigermouthbear.clantags.api.ClanTagsApi;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
	@Inject(method = "getPlayerName", at = @At("RETURN"), cancellable = true)
	public void playerNameWrapper(NetworkPlayerInfo networkPlayerInfoIn, CallbackInfoReturnable<String> cir) {
		cir.setReturnValue(ClanTagsApi.handlePlayerTab(networkPlayerInfoIn));
	}
}
