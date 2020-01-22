package me.tigermouthbear.clantags.command;

import com.google.common.collect.Lists;
import me.tigermouthbear.clantags.Globals;
import me.tigermouthbear.clantags.Utils;
import me.tigermouthbear.clantags.data.ClanMember;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class ClansCommand implements ICommand, Globals
{
	@Override
	public int compareTo(ICommand arg0)
	{
		return 1;
	}

	@Override
	public String getName()
	{
		return "clans";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/clans <username or leave blank>";
	}

	@Override
	public List<String> getAliases()
	{
		List<String> aliases = Lists.newArrayList();
		aliases.add("/getclans");
		aliases.add("/clan");
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] argString)
	{
		if(argString.length > 0)
		{
			ClanMember clanMember = ClanMember.getClanMemberByUsername(argString[0]);
			TextComponentString prefix = new TextComponentString(argString[0] + "'s clans: ");
			Utils.printComponent(prefix.appendSibling(Utils.getInteractiveClanTag(clanMember)));
		}
		else
		{
			TextComponentString prefix = new TextComponentString("All clans: ");
			Utils.printComponent(prefix.appendSibling(Utils.getAllInteractiveClanTags()));
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
	{
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
	{
		if(getClosestPlayer() != null)
		{
			List<String> list = new ArrayList<>();
			list.add(getClosestPlayer().getName());
			return list;
		}

		return null;
	}

	private EntityPlayer getClosestPlayer()
	{
		EntityPlayer closestPlayer = null;

		for(Object object: mc.world.playerEntities.stream().filter(player -> player != mc.player).toArray())
		{
			EntityPlayer player = (EntityPlayer) object;

			if(closestPlayer == null)
			{
				closestPlayer = player;
				continue;
			}

			if(player != mc.player && player.getDistanceSq(mc.player) < closestPlayer.getDistanceSq(mc.player))
			{
				closestPlayer = player;
			}
		}

		return closestPlayer;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index)
	{
		return false;
	}
}
