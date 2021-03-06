package dev.tigr.clantags.api.command;

import com.google.common.collect.Lists;
import dev.tigr.clantags.api.Utils;
import dev.tigr.clantags.api.data.Clan;
import dev.tigr.clantags.api.screen.ClanScreen;
import dev.tigr.clantags.api.ClanTags;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/***
 * @author Tigermouthbear
 * 12/30/19
 */
public class InfoCommand implements ICommand {
	private static Clan clanToOpen = null;

	public InfoCommand() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public int compareTo(ICommand arg0) {
		return 1;
	}

	@Override
	public String getName() {
		return "info";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "info <clan tag>";
	}

	@Override
	public List<String> getAliases() {
		List<String> aliases = Lists.newArrayList();
		aliases.add("clan");
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if(!execute(args)) Utils.printMessage("Clan not found!");
	}

	public boolean execute(String[] args) {
		if(args.length == 1) {
			ClanTags.MC.displayGuiScreen(null);
			clanToOpen = ClanTags.getClan(args[0]);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

	@SubscribeEvent
	public void renderTickEvent(TickEvent.RenderTickEvent event) {
		if(ClanTags.MC.currentScreen == null && clanToOpen != null) {
			ClanTags.MC.displayGuiScreen(new ClanScreen(clanToOpen));
			clanToOpen = null;
		}
	}
}