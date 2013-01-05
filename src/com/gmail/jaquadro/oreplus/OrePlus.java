package com.gmail.jaquadro.oreplus;

import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrePlus extends JavaPlugin
{
    private Configuration _config;
    private Map<World, List<OreRule>> _worldGenRules;
    private Map<World, List<ClearRule>> _worldClearRules;
    private List<OreRule> _defaultGenRules;
    private List<ClearRule> _defaultClearRules;

    @Override
    public void onEnable()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new OPListener(this), this);

        _config = getConfig();
        _worldGenRules = new HashMap<World, List<OreRule>>();
        _worldClearRules = new HashMap<World, List<ClearRule>>();

        _config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {

    }

    public List<OreRule> GetOreRules (World world) {
        if (_worldGenRules.containsKey(world))
            return _worldGenRules.get(world);

        if (_config.contains(world.getName())) {
            _worldGenRules.put(world, OreRule.LoadFromWorldConfig(_config.getConfigurationSection(world.getName())));
            return _worldGenRules.get(world);
        }

        if (_defaultGenRules != null)
            return _defaultGenRules;

        if (_config.contains("default")) {
            _defaultGenRules = OreRule.LoadFromWorldConfig(_config.getConfigurationSection("default"));
            getLogger().info("Loaded default rules: " + _defaultGenRules.size() + " generator rules found");
            return _defaultGenRules;
        }

        return null;
    }

    public List<ClearRule> GetClearRules (World world) {
        if (_worldGenRules.containsKey(world))
            return _worldClearRules.get(world);

        if (_config.contains(world.getName())) {
            _worldClearRules.put(world, ClearRule.LoadFromWorldConfig(_config.getConfigurationSection(world.getName())));
            return _worldClearRules.get(world);
        }

        if (_defaultClearRules != null)
            return _defaultClearRules;

        if (_config.contains("default")) {
            _defaultClearRules = ClearRule.LoadFromWorldConfig(_config.getConfigurationSection("default"));
            getLogger().info("Loaded default rules: " + _defaultClearRules.size() + " clear rules found");
            return _defaultClearRules;
        }

        return null;
    }
}


