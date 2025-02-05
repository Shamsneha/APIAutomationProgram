package com.thetestingacademy.APITesting_testngExample;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Testng_Example018_Testng_Enabled {
    @Test
    public void test01(){
        Assert.assertTrue(true);
    }
    @Test(enabled = false)
    public void test02(){
        Assert.assertTrue(true);
    }
    @Test
    public void test03(){
        Assert.assertTrue(true);
    }

}
