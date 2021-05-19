package com.testcy.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {

    @Test
    public void test01(){
        System.out.println("ThreaId:"+ Thread.currentThread().getId());
    }
    @Test
    public void test02(){
        System.out.println("ThreaId:"+ Thread.currentThread().getId());
    }
    @Test
    public void test03(){
        System.out.println("ThreaId:"+ Thread.currentThread().getId());
    }

}
