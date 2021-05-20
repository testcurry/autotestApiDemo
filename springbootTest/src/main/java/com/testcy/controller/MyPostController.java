package com.testcy.controller;

import com.testcy.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@RestController
@Api(description = "这是我的全部post请求的接口")
public class MyPostController {

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password) {
        if (userName.equals("Curry") && password.equals("123456")) {
            return "恭喜您登录成功！";
        }
        return "用户名或密码错误，登录失败！";

    }

    @ApiOperation(value = "查询用户的接口（返回String类型的数据）", httpMethod = "POST")
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public String getUser(HttpServletRequest request, @RequestBody User user) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "参数不合法！";
        }
        for (Cookie cookie : cookies) {
            if (user.getUserName().equals("Curry") && user.getPassword().equals("123456") && cookie.getValue().equals("true")) {
                return user.toString();
            }
        }
        return "参数不合法！";
    }

    @ApiOperation(value = "携带Cookies信息查询用户的接口(返回user类型的数据)", httpMethod = "POST")
    @RequestMapping(value = "/getUserWithCookies", method = RequestMethod.POST)
    public User getUserWithCookies(HttpServletRequest request,
                                   @RequestBody User user) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.error("无权限访问，请携带正确的cookies信息访问！");
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")
                    && cookie.getValue().equals("true")
                    && user.getUserName().equals("Curry")
                    && user.getPassword().equals("123456")) {
                return user;
            }
        }
        return null;
    }

}
