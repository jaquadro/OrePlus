package com.gmail.jaquadro.oreplus;

import junit.framework.Assert;
import org.junit.Test;

public class OPMaterialTest
{
    @Test
    public void TestOPMaterialParser ()
    {
        OPMaterial mat1 = new OPMaterial("35");
        Assert.assertEquals(35, mat1.getBlockId());
        Assert.assertEquals(0, mat1.getBlockData());
        Assert.assertTrue(mat1.isBlockValid());
        Assert.assertFalse(mat1.isDataValid());

        OPMaterial mat2 = new OPMaterial("35:14");
        Assert.assertEquals(35, mat2.getBlockId());
        Assert.assertEquals(14, mat2.getBlockData());
        Assert.assertTrue(mat2.isBlockValid());
        Assert.assertTrue(mat2.isDataValid());

        OPMaterial mat3 = new OPMaterial("IRON_ORE");
        Assert.assertEquals(15, mat3.getBlockId());
        Assert.assertEquals(0, mat3.getBlockData());
        Assert.assertTrue(mat3.isBlockValid());
        Assert.assertFalse(mat3.isDataValid());

        OPMaterial mat4 = new OPMaterial("IRON_ORE:14");
        Assert.assertEquals(15, mat4.getBlockId());
        Assert.assertEquals(14, mat4.getBlockData());
        Assert.assertTrue(mat4.isBlockValid());
        Assert.assertTrue(mat4.isDataValid());
    }
}
