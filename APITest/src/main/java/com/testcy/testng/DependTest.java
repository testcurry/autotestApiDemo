package com.testcy.testng;

import org.testng.annotations.Test;

public class DependTest {

    @Test
    public void test01(){
        System.out.println("test01执行。。。");
//        throw new RuntimeException();
    }

    @Test(dependsOnMethods = "test01")
    public void test02(){

        System.out.println("test02执行。。。");
    }

}
