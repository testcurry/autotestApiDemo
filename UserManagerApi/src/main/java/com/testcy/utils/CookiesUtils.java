package com.testcy.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
public class CookiesUtils {

    public static boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("cookie为空");
            return false;
        }
        log.info("获取的cookies信息：{}", cookies);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                log.info("cookies验证通过！");
                return true;
            }
        }
        return false;
    }
}
