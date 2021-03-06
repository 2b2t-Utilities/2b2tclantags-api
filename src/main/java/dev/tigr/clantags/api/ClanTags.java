package dev.tigr.clantags.api;

import dev.tigr.clantags.api.command.ClansCommand;
import dev.tigr.clantags.api.command.InfoCommand;
import dev.tigr.clantags.api.data.Clan;
import dev.tigr.clantags.api.data.ClanMember;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.util.ArrayList;

/***
 * @author Tigermouthbear
 * @since 3/12/19
 */
public class ClanTags {
	public static final ClansCommand CLANS_COMMAND = new ClansCommand();
	public static final InfoCommand INFO_COMMAND = new InfoCommand();
	public static final Minecraft MC = Minecraft.getMinecraft();

	//prefix for info command
	public static String PREFIX = "/";

	private static boolean loaded = false;

	public static void load() {
		if(!loaded) {
			ClanDatabase.loadDatabase();
			loaded = true;
		}
	}

	public static Clan getClan(String nameOrAbbreviation) {
		for(Clan clan: Clan.getAllClans()) {
			if(clan.getAbbreviation().equalsIgnoreCase(nameOrAbbreviation) || clan.getFullName().equalsIgnoreCase(nameOrAbbreviation))
				return clan;
		}

		return null;
	}

	public static ClanMember getClanMemberByUsername(String name) {
		ArrayList<NetworkPlayerInfo> infoMap = new ArrayList<NetworkPlayerInfo>(MC.getConnection().getPlayerInfoMap());
		NetworkPlayerInfo profile = infoMap.stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		if(profile == null) {
			return getClanMemberByUuid(MojangApi.getUuid(name));
		}

		return getClanMemberByUuid(profile.getGameProfile().getId().toString().replaceAll("-", ""));
	}

	public static ClanMember getClanMemberByUuid(String uuid) {
		for(ClanMember member: ClanMember.getAllClanMembers()) {
			if(member.getUuid().equalsIgnoreCase(uuid)) return member;
		}

		return null;
	}

	public static ITextComponent getAllInteractiveClanTags() {
		TextComponentString component = new TextComponentString("");
		for(Clan clan: Clan.getAllClans()) {
			component.appendSibling(clan.getInteractiveTag());
		}

		return component;
	}

	public static String handlePlayerTab(NetworkPlayerInfo networkPlayerInfoIn) {
		String originalName = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText(): ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
		ClanMember clanMember = ClanTags.getClanMemberByUsername(networkPlayerInfoIn.getGameProfile().getName());
		if(clanMember != null) return clanMember.getInteractiveClanTags().getFormattedText() + originalName;
		return originalName;
	}

	public static void handleClientChatReceivedEvent(ClientChatReceivedEvent event) {
		String message = event.getMessage().getUnformattedText();

		if(message.startsWith("<")) {
			String username = message.split("<")[1].split(">")[0];
			ClanMember clanMember = ClanTags.getClanMemberByUsername(username);

			if(clanMember != null)
				event.setMessage(clanMember.getInteractiveClanTags().appendSibling(event.getMessage()));
		} else if(message.contains(" whispers: ")) {
			String username = message.split(" ")[0];
			ClanMember clanMember = ClanTags.getClanMemberByUsername(username);

			if(clanMember != null)
				event.setMessage(clanMember.getInteractiveClanTags().appendSibling(event.getMessage()));
		} else if(message.startsWith("to ")) {
			String beginning = message.substring(0, message.indexOf(":"));
			String[] arr = beginning.split(" ");
			String username = arr[arr.length - 1];
			ClanMember clanMember = ClanTags.getClanMemberByUsername(username);

			TextComponentString first = new TextComponentString("to ");

			if(clanMember != null)
				event.setMessage(first.appendSibling(clanMember.getInteractiveClanTags()).appendText(username).appendText(message.substring(message.indexOf(":"))));
		}
	}
}
