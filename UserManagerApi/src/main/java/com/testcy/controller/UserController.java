package com.testcy.controller;

import com.testcy.bean.User;
import com.testcy.utils.CookiesUtils;
import com.testcy.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Api(value = "/",description = "用户管理系统接口管理平台")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "用户登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user) {

        User user1 = userMapper.queryUserByName(user.getUserName());
        log.info("根据传进来的用户的id查询到的用户是：{}", user1);
        if (user1 != null) {
            if (user.getUserName().equals(user1.getUserName())&&
            user.getPassword().equals(user1.getPassword())){
                log.info("用户的用户名是：{}", user1.getUserName());
                log.info("用户的用户名是：{}", user1.getPassword());
                Cookie cookie = new Cookie("login", "true");
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }

    @ApiOperation(value = "添加用户接口", httpMethod = "Post")
    @RequestMapping("/addUser")
    public boolean addUser(HttpServletRequest request, @RequestBody User user) {
        boolean flag = CookiesUtils.verifyCookies(request);
        if (flag){
            userMapper.addUser(user);
        }
        return flag;
    }

    @ApiOperation(value = "获取用户（列表）信息接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public List<User> getUserList(HttpServletRequest request, @RequestBody User user){
        boolean flag = CookiesUtils.verifyCookies(request);
        if (flag){
            List<User> users=userMapper.getUserInfo(user);
            log.info("获取到的用户信息：{}",users);
            return users;
        }
        return null;
    }

    @ApiOperation(value = "更新用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody User user){
        boolean flag = CookiesUtils.verifyCookies(request);
        if (flag){
            int i = userMapper.updateUserInfo(user);
            log.info("更新的用户信息的数量：{}",i);
            return i;
        }
        return 0;
    }

    /*private boolean verifyCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("cookie为空");
            return false;
        }
        log.info("获取的cookies信息：{}",cookies);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                log.info("cookies验证通过！");
                return true;
            }
        }
        return false;
    }*/

}
