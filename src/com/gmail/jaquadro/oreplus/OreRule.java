package com.gmail.jaquadro.oreplus;

import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OreRule
{
    private boolean _enabled;
    private OPMaterial _material;
    private int _size;
    private int _rounds;
    private int _minHeight;
    private int _maxHeight;
    private double _prob;

    private List<Biome> _includedBiomes;

    public OreRule (ConfigurationSection config) {
        _material = new OPMaterial(config.getString("block", ""));

        _enabled = config.getBoolean("enabled", true);
        _size = config.getInt("size", 8);
        _rounds = config.getInt("rounds", 1);
        _minHeight = config.getInt("min-height", 0);
        _maxHeight = config.getInt("max-height", 64);
        _prob = config.getDouble("probability", 1.0);

        _includedBiomes = processBiomeList(config.getStringList("biomes"));
    }

    @SuppressWarnings("unchecked")
    public static List<OreRule> LoadFromList (List<Map<?, ?>> configList) {
        List<OreRule> result = new ArrayList<OreRule>();
        if (configList == null)
            return result;

        for (Map<?, ?> item : configList) {
            Map<String, Object> castItem = (Map<String, Object>)item;

            MemoryConfiguration config = new MemoryConfiguration();
            config.addDefaults(castItem);
            result.add(new OreRule(config.getDefaults()));
        }

        return result;
    }

    public static List<OreRule> LoadFromWorldConfig (ConfigurationSection worldConfig) {
        if (!worldConfig.contains("generate"))
            return new ArrayList<OreRule>();
        else
            return LoadFromList(worldConfig.getMapList("generate"));
    }

    public boolean isEnabled() {
        return _enabled;
    }

    public OPMaterial getMaterial () {
        return _material;
    }

    public int getSize () {
        return _size;
    }

    public int getRounds () {
        return _rounds;
    }

    public int getMinHeight () {
        return _minHeight;
    }

    public int getMaxHeight () {
        return _maxHeight;
    }

    public double getProbability() {
        return _prob;
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
