package com.tigermouthbear.clantags;

import com.tigermouthbear.clantags.api.WebApi;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

@Mod(modid = ClanTags.MODID, name = ClanTags.NAME, version = ClanTags.VERSION)
public class ClanTags
{
    public static final String MODID = "2b2tclantags";
    public static final String NAME = "2b2tclantags";
    public static final String VERSION = "0.1";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        WebApi.loadClans();
        FMLLog.log.info(Clan.getAllClans().get(0).getFullName());
    }
}
