package com.testcy.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "Teacher")
public class GroupsOnClass3 {

    public void teacher1(){
        System.out.println("GroupsOnClass3中的teacher1执行了。。。");
    }

    public void teacher2(){
        System.out.println("GroupsOnClass3中的teacher2执行了。。。");
    }


}
