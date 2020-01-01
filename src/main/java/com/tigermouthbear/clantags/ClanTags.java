package com.tigermouthbear.clantags;

import com.tigermouthbear.clantags.api.ClanTagsApi;
import com.tigermouthbear.clantags.command.ClansCommand;
import com.tigermouthbear.clantags.command.InfoCommand;
import com.tigermouthbear.clantags.impl.ChatPrefix;
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
    public static final String VERSION = "0.1";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClanTagsApi.loadClans();

        ClientCommandHandler.instance.registerCommand(new InfoCommand());
        ClientCommandHandler.instance.registerCommand(new ClansCommand());

        MinecraftForge.EVENT_BUS.register(new ChatPrefix());
    }
}
