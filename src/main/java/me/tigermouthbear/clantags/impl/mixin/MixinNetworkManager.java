package me.tigermouthbear.clantags.impl.mixin;

import io.netty.channel.ChannelHandlerContext;
import me.tigermouthbear.clantags.impl.PacketReceivedEvent;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Tigermouthbear
 * 3/12/19
 */
@Mixin(NetworkManager.class)
public class MixinNetworkManager {
	@Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
	private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
		PacketReceivedEvent event = new PacketReceivedEvent(packet);
		MinecraftForge.EVENT_BUS.post(event);
		if(event.isCanceled() && callbackInfo.isCancellable()) callbackInfo.cancel();
	}
}
