package com.testcy.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){

        System.out.println("name="+name+";age="+age);

    }

    @DataProvider
    public Object[][] data(){
        return new Object[][]{
                {"Curry",18},
                {"Jerry",20},
                {"Tom",30}
        };
    }

    @Test(dataProvider = "methodData")
    public void test01(String name,int age){
        System.out.println("test01-name="+name+";age="+age);
    }

    @Test(dataProvider = "methodData")
    public void test02(String name,int age){
        System.out.println("test02-name="+name+";age="+age);
    }

    @DataProvider
    public Object[][] methodData(Method method){
        Object[][] result =null;
        if (method.getName().equals("test01")){
            result=new Object[][]{
                    {"test01",18},
                    {"test01",20}
            };
        }else if (method.getName().equals("test02")){
            result=new Object[][]{
                    {"test02",18},
                    {"test02",20}
            };
        }
        return result;

    }

}
