package me.tigermouthbear.clantags.command;

import com.google.common.collect.Lists;
import me.tigermouthbear.clantags.Globals;
import me.tigermouthbear.clantags.Utils;
import me.tigermouthbear.clantags.data.ClanMember;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

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
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] argString)
	{
		if(argString.length > 0)
		{
			ClanMember clanMember = ClanMember.getClanMemberByUsername(argString[0]);
			Utils.printComponent(Utils.getInteractiveClanTag(clanMember));
		}
		else
		{
			Utils.printComponent(Utils.getAllInteractiveClanTags());
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
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index)
	{
		return false;
	}
}
