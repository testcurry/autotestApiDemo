package com.testcy.testng;

import org.testng.annotations.Test;

public class TImeOutTest {

    @Test(timeOut = 1000)
    public void test01() throws InterruptedException {
        Thread.sleep(500);
    }

    @Test(timeOut = 1000)
    public void test02() throws InterruptedException {
        Thread.sleep(1000);
    }

}
