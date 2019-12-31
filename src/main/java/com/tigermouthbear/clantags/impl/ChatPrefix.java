package com.tigermouthbear.clantags.impl;

import com.tigermouthbear.clantags.Globals;
import com.tigermouthbear.clantags.api.MojangApi;
import com.tigermouthbear.clantags.data.Clan;
import com.tigermouthbear.clantags.data.ClanMember;
import com.tigermouthbear.clantags.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatPrefix
{
	public ChatPrefix()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onChatMessage(ClientChatReceivedEvent event)
	{
		String name = event.getMessage().getUnformattedText().split("<")[1].split(">")[0];
		ClanMember clanMember = ClanMember.getClanMemberByUuid(MojangApi.getUuid(name));

		ChatUtils.printMessage(MojangApi.getUuid(name));
		ChatUtils.printMessage(clanMember.getClans().toString());

		/*if(clanMember != null)
		{
			StringBuilder tag = new StringBuilder();
			for(Clan clan: clanMember.getClans())
			{
				tag.append("[" + clan.getAbbreviation() + "]");
			}

			event.setMessage(new TextComponentString(tag.toString() + event.getMessage()));
		}*/
	}
}
