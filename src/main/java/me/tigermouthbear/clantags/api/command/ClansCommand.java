package me.tigermouthbear.clantags.api.command;

import com.google.common.collect.Lists;
import me.tigermouthbear.clantags.api.ClanTagsApi;
import me.tigermouthbear.clantags.api.Utils;
import me.tigermouthbear.clantags.api.data.ClanMember;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

import static me.tigermouthbear.clantags.api.ClanTagsApi.MC;

/**
 * @author Tigermouthbear
 * @since 3/12/20
 */
public class ClansCommand implements ICommand {
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
		executeClanCommand(args);
	}

	public boolean executeClanCommand(String[] args) {
		if(args.length == 1) {
			ClanMember clanMember = ClanTagsApi.getClanMemberByUsername(args[0]);
			TextComponentString prefix = new TextComponentString(args[0] + "'s clans: ");
			Utils.printComponent(prefix.appendSibling(ClanTagsApi.getInteractiveClanTag(clanMember)));
			return true;
		} else if(args.length == 0) {
			TextComponentString prefix = new TextComponentString("All clans: ");
			Utils.printComponent(prefix.appendSibling(ClanTagsApi.getAllInteractiveClanTags()));
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
		return getClanTabComplete();
	}

	public List<String> getClanTabComplete() {
		if(getClosestPlayer() != null) {
			List<String> list = new ArrayList<>();
			list.add(getClosestPlayer().getName());
			return list;
		}

		return null;
	}

	private EntityPlayer getClosestPlayer() {
		EntityPlayer closestPlayer = null;

		for(Object object: MC.world.playerEntities.stream().filter(player -> player != MC.player).toArray()) {
			EntityPlayer player = (EntityPlayer) object;

			if(closestPlayer == null) {
				closestPlayer = player;
				continue;
			}

			if(player != MC.player && player.getDistanceSq(MC.player) < closestPlayer.getDistanceSq(MC.player)) {
				closestPlayer = player;
			}
		}

		return closestPlayer;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}
}
