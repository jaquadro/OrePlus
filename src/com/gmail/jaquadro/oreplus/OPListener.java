package com.gmail.jaquadro.oreplus;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;

public class OPListener implements Listener
{
    private OrePlus _plugin;

    public OPListener(OrePlus plugin) {
        _plugin = plugin;
    }

    @EventHandler
    public void onWorldInit (WorldInitEvent event)
    {
        for (BlockPopulator pop : event.getWorld().getPopulators()) {
            if (pop instanceof OrePopulator)
                return;
        }

        if (event.getWorld().getEnvironment() == World.Environment.NORMAL) {
            event.getWorld().getPopulators().add(new OrePopulator(_plugin));
        }
    }
}
