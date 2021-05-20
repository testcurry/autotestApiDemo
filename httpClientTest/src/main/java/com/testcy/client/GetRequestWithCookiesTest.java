package com.testcy.client;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetRequestWithCookiesTest {

    String url;
    private ResourceBundle resourceBundle;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest() {
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = resourceBundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String uri=resourceBundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        HttpGet get = new HttpGet(testUrl);
        HttpResponse response = httpClient.execute(get);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.cookieStore = httpClient.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue()+";");
        }


    /*    Header[] headers = response.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            System.out.println(headers[i]);
        }
        System.out.println();*/

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testWithCookies() throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.setCookieStore(this.cookieStore);
        String testUrl=this.url+resourceBundle.getString("test.getWithCookies");
        HttpGet httpGet = new HttpGet(testUrl);
        HttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.getStatusLine());
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }

}
