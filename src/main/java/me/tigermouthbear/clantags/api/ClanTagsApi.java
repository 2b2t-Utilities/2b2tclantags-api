package me.tigermouthbear.clantags.api;

import me.tigermouthbear.clantags.api.command.ClansCommand;
import me.tigermouthbear.clantags.api.command.InfoCommand;
import me.tigermouthbear.clantags.api.data.Clan;
import me.tigermouthbear.clantags.api.data.ClanMember;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

/***
 * @author Tigermouthbear
 * @since 3/12/19
 */
public class ClanTagsApi {
	public static Minecraft MC;

	public static void loadClans(Minecraft minecraft) {
		MC = minecraft;
		DatabaseApi.loadDatabase(minecraft);
	}

	public static Clan getClan(String nameOrAbbreviation) {
		for(Clan clan: Clan.getAllClans()) {
			if(clan.getAbbreviation().equalsIgnoreCase(nameOrAbbreviation) || clan.getFullName().equalsIgnoreCase(nameOrAbbreviation)) return clan;
		}

		return null;
	}

	public static ClanMember getClanMemberByUsername(String name) {
		for(EntityPlayer player: MC.world.playerEntities) {
			if(player.getName().equals(name)) return getClanMemberByUuid(player.getUniqueID().toString().replaceAll("-", ""));
		}

		return getClanMemberByUuid(MojangApi.getUuid(name));
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
		String originalName = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
		ClanMember clanMember = ClanTagsApi.getClanMemberByUsername(networkPlayerInfoIn.getGameProfile().getName());
		if(clanMember != null) return clanMember.getInteractiveClanTags().getFormattedText() + originalName;
		return originalName;
	}

	public static void handleClientChatReceivedEvent(ClientChatReceivedEvent event) {
		String message = event.getMessage().getUnformattedText();

		if(message.startsWith("<")) {
			String username = message.split("<")[1].split(">")[0];
			ClanMember clanMember = ClanTagsApi.getClanMemberByUsername(username);

			if(clanMember != null) event.setMessage(clanMember.getInteractiveClanTags().appendSibling(event.getMessage()));
		}
		else if(message.contains(" whispers: ")) {
			String username = message.split(" ")[0];
			ClanMember clanMember = ClanTagsApi.getClanMemberByUsername(username);

			if(clanMember != null) event.setMessage(clanMember.getInteractiveClanTags().appendSibling(event.getMessage()));
		}
		else if(message.startsWith("to ")) {
			String beginning = message.substring(0, message.indexOf(":"));
			String[] arr = beginning.split(" ");
			String username = arr[arr.length - 1];
			ClanMember clanMember = ClanTagsApi.getClanMemberByUsername(username);

			TextComponentString first = new TextComponentString("to ");

			if(clanMember != null) event.setMessage(first.appendSibling(clanMember.getInteractiveClanTags()).appendText(username).appendText(message.substring(message.indexOf(":"))));
		}
	}

	public static class Command {
		public static final ClansCommand CLANS_COMMAND = new ClansCommand();
		public static final InfoCommand INFO_COMMAND = new InfoCommand();
	}
}
