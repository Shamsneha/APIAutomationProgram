package com.thetestingacademy.APITesting_testngExample;

import org.testng.Assert;
import org.testng.annotations.Test;
import sun.font.TrueTypeFont;

public class Testng_Example014 {
    @Test(groups = )
    public void sanityRun(){
        System.out.println("sanity");
        System.out.println("QA");
        Assert.assertTrue(true);

    }
    @Test
    public void RegRun(){
        System.out.println("Reg");
        Assert.assertTrue(false);
    }
    @Test
    public void SmokeRun(){
        System.out.println("Smoke");
        Assert.assertTrue(true);
    }
    @Test
    public void SanityRun1(){
        System.out.println("Sanity");
        System.out.println("Sanity");
        Assert.assertTrue(true);
    }
    @Test
    public void SmokeRun2(){
        System.out.println("Smoke");
        Assert.assertTrue(false);
    }

}