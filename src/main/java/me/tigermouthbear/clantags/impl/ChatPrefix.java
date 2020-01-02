package me.tigermouthbear.clantags.impl;

import me.tigermouthbear.clantags.data.Clan;
import me.tigermouthbear.clantags.data.ClanMember;
import me.tigermouthbear.clantags.Utils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
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
		String message = event.getMessage().getUnformattedText();

		//regular message
		if(message.startsWith("<"))
		{
			String username = message.split("<")[1].split(">")[0];
			ClanMember clanMember = ClanMember.getClanMemberByUsername(username);

			if(clanMember != null) event.setMessage(Utils.getInteractiveClanTag(clanMember).appendSibling(event.getMessage()));
		}
		else if(message.contains(" whispers to you: "))
		{
			String username = message.split(" ")[0];
			ClanMember clanMember = ClanMember.getClanMemberByUsername(username);

			if(clanMember != null) event.setMessage(Utils.getInteractiveClanTag(clanMember).appendSibling(event.getMessage()));
		}
		else if(message.startsWith("You whisper to "))
		{
			String beginning = message.substring(0, message.indexOf(":"));
			String[] arr = beginning.split(" ");
			String username = arr[arr.length - 1];
			ClanMember clanMember = ClanMember.getClanMemberByUsername(username);

			TextComponentString first = new TextComponentString("You whisper to ");

			if(clanMember != null) event.setMessage(first.appendSibling(Utils.getInteractiveClanTag(clanMember)).appendText(message.substring(message.indexOf(":"))));
		}
	}
}