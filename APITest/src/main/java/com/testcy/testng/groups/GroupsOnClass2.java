package com.testcy.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public void stu1(){
        System.out.println("GroupsOnClass2中的stu1方法执行了。。。。");
    }

    public void stu2(){
        System.out.println("GroupsOnClass2中的stu2方法执行了。。。。");
    }


}
