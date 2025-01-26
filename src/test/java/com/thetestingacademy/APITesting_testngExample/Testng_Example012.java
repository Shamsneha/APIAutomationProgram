package com.thetestingacademy.APITesting_testngExample;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Testng_Example012 {

    @BeforeTest
    public void getToken(){
        System.out.println("1");
    }
    @BeforeTest
    public void getBookingID(){
        System.out.println("2");
    }
    @Test
    public void test_put(){
        System.out.println("3");
    }
}
