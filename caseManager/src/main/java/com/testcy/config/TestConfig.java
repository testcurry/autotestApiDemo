package com.testcy.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSessionFactory;

public class TestConfig {

    public static String loginUrl;
    public static String addUserUrl;
    public static String getUserInfoUrl;
    public static String getUserListUrl;
    public static String updateUserInfoUrl;

    public static DefaultHttpClient client;
    public CookieStore cookieStore;
}
