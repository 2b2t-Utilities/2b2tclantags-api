package dev.tigr.clantags.api.command;

import com.google.common.collect.Lists;
import dev.tigr.clantags.api.Utils;
import dev.tigr.clantags.api.data.ClanMember;
import dev.tigr.clantags.api.ClanTags;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tigermouthbear
 * @since 3/12/20
 */
public class ClansCommand implements ICommand {
	private static final DistanceComparator distanceComparator = new DistanceComparator();

	@Override
	public int compareTo(ICommand arg0) {
		return 1;
	}

	@Override
	public String getName() {
		return "clans";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "clans <username or leave blank>";
	}

	@Override
	public List<String> getAliases() {
		List<String> aliases = Lists.newArrayList();
		aliases.add("getclans");
		aliases.add("clan");
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if(args.length != 0 && args.length != 1) Utils.printMessage("Command not found!");
		if(!executeClanCommand(args)) Utils.printMessage("Player not found!");
	}

	public boolean executeClanCommand(String[] args) {
		if(args.length == 1) {
			ClanMember clanMember = ClanTags.getClanMemberByUsername(args[0]);
			if(clanMember == null) return false;
			TextComponentString prefix = new TextComponentString(args[0] + "'s clans: ");
			Utils.printComponent(prefix.appendSibling(clanMember.getInteractiveClanTags()));
			return true;
		} else if(args.length == 0) {
			TextComponentString prefix = new TextComponentString("All clans: ");
			Utils.printComponent(prefix.appendSibling(ClanTags.getAllInteractiveClanTags()));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		List<String> list = new ArrayList<>();
		for(EntityPlayer entityPlayer: ClanTags.MC.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != ClanTags.MC.player).sorted(distanceComparator).collect(Collectors.toList()))
			list.add(entityPlayer.getName());
		return list;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}
}

class DistanceComparator implements Comparator<EntityPlayer> {
	public int compare(EntityPlayer e1, EntityPlayer e2) {
		return Float.compare(e1.getDistance(ClanTags.MC.player), e2.getDistance(ClanTags.MC.player));
	}
}
