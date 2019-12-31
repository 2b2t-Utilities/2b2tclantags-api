package com.tigermouthbear.clantags.utils;

import com.tigermouthbear.clantags.data.Clan;
import net.minecraft.util.text.ITextComponent;

public class ChatUtils
{
	public static ITextComponent getInteractiveClanTag(Clan clan)
	{
		return ITextComponent.Serializer.jsonToComponent("{\"text\":\"[" + clan.getAbbreviation() + "] \",\"color\":\"" + clan.getColor() + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"info " + clan.getAbbreviation() + "\"}}");
	}
}
