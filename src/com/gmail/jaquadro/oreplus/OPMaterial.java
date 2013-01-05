package com.gmail.jaquadro.oreplus;

import org.bukkit.Material;

import java.util.StringTokenizer;

public class OPMaterial
{
    private boolean _blockValid = true;
    private boolean _dataValid = false;
    private int _id;
    private int _data;

    public OPMaterial (String str) {
        try {
            Material mat = Material.valueOf(str.toUpperCase());
            _id = mat.getId();
            return;
        }
        catch (Exception e) { }

        try {
            _id = Integer.parseInt(str);
            return;
        }
        catch (Exception e) { }

        try {
            StringTokenizer tok = new StringTokenizer(str);
            Material mat = Material.valueOf(tok.nextToken(":").toUpperCase());
            _id = mat.getId();
            _data = Integer.parseInt(tok.nextToken(":"));
            _dataValid = true;
            return;
        }
        catch (Exception e) { }

        try {
            StringTokenizer tok = new StringTokenizer(str);
            _id = Integer.parseInt(tok.nextToken(":"));
            _data = Integer.parseInt(tok.nextToken(":"));
            _dataValid = true;
            return;
        }
        catch (Exception e) { }

        _blockValid = false;
    }

    public OPMaterial (int id) {
        _id = id;
    }

    public OPMaterial (int id, int data) {
        _id = id;
        _data = data;
        _dataValid = true;
    }

    public boolean isBlockValid () {
        return _blockValid;
    }

    public boolean isDataValid() {
        return _dataValid;
    }

    public int getBlockId () {
        return _id;
    }

    public int getBlockData () {
        return _data;
    }
}
