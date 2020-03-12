package me.tigermouthbear.clantags.impl;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author Tigermouthbear
 * 3/12/19
 */
@Cancelable
public class PacketReceivedEvent extends Event {
	private Packet packet;

	public PacketReceivedEvent(Packet packet) {
		this.packet = packet;
	}

	public Packet getPacket() {
		return packet;
	}
}
