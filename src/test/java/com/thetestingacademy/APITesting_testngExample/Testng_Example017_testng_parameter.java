package com.thetestingacademy.APITesting_testngExample;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class Testng_Example017_testng_parameter {
    @Parameters("browser")
    @Test
    void demo1(String value) {
        System.out.println("Browser is " + value);
        // Open the Browser and select dadadada
        if (value.equalsIgnoreCase("chrome")) {
            System.out.println("Start my Testing");
        }
        if (value.equalsIgnoreCase("firefox")) {
            System.out.println("Start my Firefox");
        }
    }

}
