package com.tigermouthbear.clantags.impl;

import com.tigermouthbear.clantags.data.Clan;
import com.tigermouthbear.clantags.data.ClanMember;
import com.tigermouthbear.clantags.utils.ChatUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class ChatPrefix
{
	public ChatPrefix()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent()
	public void onChatMessage(ClientChatReceivedEvent event)
	{
		String username = event.getMessage().getUnformattedText().split("<")[1].split(">")[0];
		ClanMember clanMember = ClanMember.getClanMemberByUsername(username);

		if(clanMember != null)
		{
			ITextComponent tags = ITextComponent.Serializer.jsonToComponent("");
			for(Clan clan: clanMember.getClans())
			{
				tags.appendSibling(ChatUtils.getInteractiveClanTag(clan));
			}

			event.setMessage(tags.appendSibling(event.getMessage()));
		}
	}
}
