package com.tigermouthbear.clantags.utils;

import com.tigermouthbear.clantags.Globals;
import com.tigermouthbear.clantags.data.Clan;
import com.tigermouthbear.clantags.data.ClanMember;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class ChatUtils implements Globals
{
	public static void printmessage(String message)
	{
		mc.ingameGUI.addChatMessage(ChatType.SYSTEM, new TextComponentString(message));
	}

	public static ITextComponent getInteractiveClanTag(ClanMember member)
	{

		ITextComponent component = new ITextComponent.Serializer().jsonToComponent("");
		for(Clan clan: member.getClans())
		{
			component.appendSibling(ITextComponent.Serializer.jsonToComponent("{\"text\":\"[" + clan.getAbbreviation() + "] \",\"color\":\"" + clan.getColor() + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"info " + clan.getAbbreviation() + "\"}}"));
		}

		return component;
	}
}
