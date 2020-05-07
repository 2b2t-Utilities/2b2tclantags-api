package me.tigermouthbear.clantags.api;

import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/***
 * @author Tigermouthbear
 * @since 12/30/19
 */
public class Utils {
	public static void printComponent(ITextComponent component) {
		ClanTags.MC.ingameGUI.addChatMessage(ChatType.SYSTEM, component);
	}

	public static void printMessage(String text) {
		ClanTags.MC.ingameGUI.addChatMessage(ChatType.SYSTEM, new TextComponentString(text));
	}

	public static final Map<String, String> colors = new HashMap<String, String>() {{
		put("black", "000000");
		put("dark_blue", "0000AA");
		put("dark_green", "00AA00");
		put("dark_aqua", "00AAAA");
		put("dark_red", "AA0000");
		put("dark_purple", "AA00AA");
		put("gold", "FFAA00");
		put("gray", "AAAAAA");
		put("dark_gray", "555555");
		put("blue", "5555FF");
		put("green", "55FF55");
		put("aqua", "55FFFF");
		put("red", "FF5555");
		put("light_purple", "FF55FF");
		put("yellow", "FFFF55");
		put("white", "FFFFFF");
	}};

	public static void openLink(String link) {
		try {
			URI uri = new URI(link);
			Class<?> oclass = Class.forName("java.awt.Desktop");
			Object object = oclass.getMethod("getDesktop").invoke(null);
			oclass.getMethod("browse", URI.class).invoke(object, uri);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
