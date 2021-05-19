package com.testcy.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {


    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite执行了。。。");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite执行了。。。");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest执行了。。。");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest执行了。。。");
    }

}
