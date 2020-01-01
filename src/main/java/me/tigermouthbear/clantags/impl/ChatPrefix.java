package me.tigermouthbear.clantags.impl;

import me.tigermouthbear.clantags.data.ClanMember;
import me.tigermouthbear.clantags.Utils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class ChatPrefix
{
	@SubscribeEvent()
	public void onChatMessage(ClientChatReceivedEvent event)
	{
		//regular message
		if(event.getMessage().getUnformattedText().startsWith("<"))
		{
			String username = event.getMessage().getUnformattedText().split("<")[1].split(">")[0];
			ClanMember clanMember = ClanMember.getClanMemberByUsername(username);

			if(clanMember != null) event.setMessage(Utils.getInteractiveClanTag(clanMember).appendSibling(event.getMessage()));
		}

		//whispers
		if(event.getMessage().getUnformattedText().split(" ")[1].startsWith("whispers: "))
		{
			String username = event.getMessage().getUnformattedText().split(" ")[0];
			ClanMember clanMember = ClanMember.getClanMemberByUsername(username);

			if(clanMember != null) event.setMessage(Utils.getInteractiveClanTag(clanMember).appendSibling(event.getMessage()));
		}
	}
}
