package com.testcy.reports;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestReport {

    @Test
    public void testDemo1(){
        Assert.assertEquals(1, 2);
    }

    @Test
    public void testDemo2(){
        Assert.assertEquals("aaa", "aaa");
    }

    @Test
    public void testDemo3(){
        Assert.assertEquals(1, 1);
    }

    @Test
    public void logDemo() {
        Reporter.log("这是我们自己写的日志。。。");
    }

}
