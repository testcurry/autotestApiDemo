package com.testcy.testng;

import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void test01() {
        System.out.println("test01执行了。。。");
    }

    @Test(enabled = false)
    public void test02() {
        System.out.println("test02执行了。。。");
    }

    @Test(enabled = true)
    public void test03() {
        System.out.println("test03执行了。。。");
    }

}
