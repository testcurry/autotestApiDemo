package com.testcy.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ResourceBundle;

public class PostRequestWithCookiesTest {
    String url;
    private ResourceBundle resourceBundle;
    private CookieStore cookieStore;
    private DefaultHttpClient httpClient;

    @BeforeTest
    public void init() {
        this.resourceBundle = ResourceBundle.getBundle("application");
        this.url=resourceBundle.getString("test.url");
    }
    @Test
    public void getCookies() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String uri=resourceBundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        HttpGet get = new HttpGet(testUrl);
        HttpResponse response = httpClient.execute(get);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.cookieStore = httpClient.getCookieStore();
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void postWithCookies() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String testUrl = this.url+resourceBundle.getString("test.postWithCookies");
        HttpPost httpPost = new HttpPost(testUrl);
        //添加请求头信息，正文的格式
        httpPost.setHeader("Content-Type","application/json");
        //添加要携带的Cookie信息
        httpClient.setCookieStore(this.cookieStore);
        //添加json格式的body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Tom");
        jsonObject.put("age", "18");
        //将body添加到请求中
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        httpPost.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        JSONObject jsonObjResult = new JSONObject(result);
        String msg = (String) jsonObjResult.get("msg");
        String status = (String) jsonObjResult.get("status");
        Assert.assertEquals("success", msg);
        Assert.assertEquals("201", status);
    }

}
