package com.testcy.testng;

import org.testng.annotations.Test;

public class ExceptedExceptionTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试。。。");
        throw new RuntimeException("运行异常了。。。");
    }

}
