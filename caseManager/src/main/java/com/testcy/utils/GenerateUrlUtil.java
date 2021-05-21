package com.testcy.utils;

import com.testcy.bean.InterfaceEnum;

import javax.xml.stream.Location;
import java.util.Locale;
import java.util.ResourceBundle;

public class GenerateUrlUtil {

    public static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceEnum name) {
        String baseUrl = bundle.getString("test.url");
        String uri = null;
        String testUrl = null;

        if (name.equals(InterfaceEnum.AddUserCase)) {
            uri = bundle.getString("addUser.uri");
        } else if (name.equals(InterfaceEnum.GetUserInfoCase)) {
            uri = bundle.getString("getUserInfo.uri");
        } else if (name.equals(InterfaceEnum.GetUserListCase)) {
            uri = bundle.getString("getUserList.uri");
        } else if (name.equals(InterfaceEnum.LoginCase)) {
            uri = bundle.getString("login.uri");
        }else if (name.equals(InterfaceEnum.UpdateUserInfoCase)) {
            uri = bundle.getString("updateUserInfo.uri");
        }

        testUrl = baseUrl + uri;
        return testUrl;
    }

}
