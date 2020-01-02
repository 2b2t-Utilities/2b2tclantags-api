package me.tigermouthbear.clantags;

import me.tigermouthbear.clantags.api.ClanTagsApi;
import me.tigermouthbear.clantags.command.ClansCommand;
import me.tigermouthbear.clantags.command.InfoCommand;
import me.tigermouthbear.clantags.impl.ChatPrefix;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

@Mod(modid = ClanTags.MODID, name = ClanTags.NAME, version = ClanTags.VERSION, clientSideOnly = true)
public class ClanTags
{
    public static final String MODID = "2b2tclantags";
    public static final String NAME = "2b2tclantags";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClanTagsApi.loadClans();

        ClientCommandHandler.instance.registerCommand(new InfoCommand());
        ClientCommandHandler.instance.registerCommand(new ClansCommand());

        MinecraftForge.EVENT_BUS.register(new ChatPrefix());
    }
}
