package com.gmail.jaquadro.oreplus;

import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClearRule
{
    private boolean _enabled;
    private OPMaterial _material;
    private OPMaterial _clearMaterial;
    private int _minHeight;
    private int _maxHeight;

    private List<Biome> _includedBiomes;

    public ClearRule (ConfigurationSection config) {
        _material = new OPMaterial(config.getString("block", ""));
        _clearMaterial = new OPMaterial(config.getString("replacement", ""));

        _enabled = config.getBoolean("enabled", true);
        _minHeight = config.getInt("min-height", 0);
        _maxHeight = config.getInt("max-height", 64);

        _includedBiomes = processBiomeList(config.getStringList("biomes"));
    }

    @SuppressWarnings("unchecked")
    public static List<ClearRule> LoadFromList (List<Map<?, ?>> configList) {
        List<ClearRule> result = new ArrayList<ClearRule>();
        if (configList == null)
            return result;

        for (Map<?, ?> item : configList) {
            Map<String, Object> castItem = (Map<String, Object>)item;

            MemoryConfiguration config = new MemoryConfiguration();
            config.addDefaults(castItem);
            result.add(new ClearRule(config.getDefaults()));
        }

        return result;
    }

    public static List<ClearRule> LoadFromWorldConfig (ConfigurationSection worldConfig) {
        if (!worldConfig.contains("clear"))
            return new ArrayList<ClearRule>();
        else
            return LoadFromList(worldConfig.getMapList("clear"));
    }

    public boolean isEnabled() {
        return _enabled;
    }

    public OPMaterial getMaterial () {
        return _material;
    }

    public OPMaterial getClearMaterial () {
        return _clearMaterial;
    }

    public int getMinHeight () {
        return _minHeight;
    }

    public int getMaxHeight () {
        return _maxHeight;
    }

    public List<Biome> getIncludedBiomes () {
        return _includedBiomes;
    }

    private static List<Biome> processBiomeList (List<String> biomes) {
        List<Biome> result = new ArrayList<Biome>();
        if (biomes == null)
            return result;

        for (String name : biomes) {
            try {
                result.add(Biome.valueOf(name.toUpperCase()));
            }
            catch (Exception e) {
                continue;
            }
        }

        return result;
    }
}
