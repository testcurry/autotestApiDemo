package com.testcy.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethodTest {

    @Test(groups = "server")
    public void test01(){
        System.out.println("这是服务端组的测试方法01。。。");
    }
    @Test(groups = "server")
    public void test02(){
        System.out.println("这是服务端组的测试方法02。。。");
    }

    @Test(groups = "client")
    public void test03() {
        System.out.println("这是客户端组的测试方法03。。。");
    }

    @Test(groups = "client")
    public void test04() {
        System.out.println("这是客户端组的测试方法04。。。");
    }

    @BeforeGroups("server")
    public void beforeGroupOnServer(){
        System.out.println("beforeGroupOnServer方法执行了。。。");
    }

    @AfterGroups("server")
    public void afterGroupOnServer(){
        System.out.println("afterGroupOnServer方法执行了。。。");
    }

    @BeforeGroups("client")
    public void beforeGroupOnClient() {
        System.out.println("beforeGroupOnClient方法执行了。。。");
    }

    @AfterGroups("client")
    public void afterGroupOnClient() {
        System.out.println("afterGroupOnClient方法执行了。。。");
    }

}
