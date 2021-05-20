package com.testcy.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void test01() throws IOException {

        HttpClient client=new DefaultHttpClient();
        String result;
        HttpGet get=new HttpGet("http://www.baidu.com/");
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, "utf-8");
        System.out.println(result);

    }

}
