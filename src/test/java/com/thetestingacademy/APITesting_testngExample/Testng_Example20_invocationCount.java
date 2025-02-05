package com.thetestingacademy.APITesting_testngExample;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Testng_Example20_invocationCount {
    @Test(invocationCount = 5)
    public void test01(){
        Assert.assertTrue(true);
    }

}
