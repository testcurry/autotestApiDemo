package com.testcy.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadTest {

    @Test(invocationCount = 10,threadPoolSize = 3)
    public void threadTest(){
        System.out.println(1);
        System.out.printf("ThreadId:%s%n", Thread.currentThread().getId());
    }

}
