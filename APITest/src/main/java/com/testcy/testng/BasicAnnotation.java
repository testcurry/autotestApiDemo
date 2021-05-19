package com.testcy.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    @Test
    public void testCase01(){
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase02(){
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod执行。。。");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod执行。。。");

    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass执行。。。");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass执行。。。");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite执行。。。");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite执行。。。");
    }
}
