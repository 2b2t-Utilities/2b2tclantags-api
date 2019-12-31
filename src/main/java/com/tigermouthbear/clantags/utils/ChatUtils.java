package com.tigermouthbear.clantags.utils;

import com.tigermouthbear.clantags.Globals;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;

public class ChatUtils implements Globals
{
	public static void printMessage(String message)
	{
		mc.ingameGUI.addChatMessage(ChatType.SYSTEM, new TextComponentString(message));
	}
}
