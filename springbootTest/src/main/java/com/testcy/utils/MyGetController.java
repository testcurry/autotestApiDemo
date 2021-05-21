package com.testcy.utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetController {

    @ApiOperation(value = "获取Cookies信息的接口",httpMethod = "GET")
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获取Cookie信息成功！";
    }

    @ApiOperation(value = "携带cookies信息才能访问的get请求接口",httpMethod = "GET")
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "访问失败，请携带正确的Cookies信息访问0。。。";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "访问成功，这是个携带cookie信息才可以访问的get请求。。。";
            }
        }
        return "访问失败，请携带正确的Cookies信息访问。。。";
    }

    @ApiOperation(value = "带参数的get请求",httpMethod = "GET")
    @RequestMapping(value = "/get/with/param")
    public Map<String,Object> getList(@RequestParam("start") Integer start,
                                      @RequestParam("end") Integer end){
        Map<String,Object> map=new HashMap<>();
        map.put("球鞋", "800");
        map.put("运动服", "300");
        map.put("篮球", "300");
        return map;
    }

    @ApiOperation(value = "带路径参数的get请求",httpMethod = "GET")
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    public Map<String,Object> getListPath(@PathVariable("start") Integer start,
                                          @PathVariable("end") Integer end){
        Map<String, Object> map = new HashMap<>();
        map.put("球鞋", "800");
        map.put("运动服", "300");
        map.put("篮球", "300");
        return map;
    }
}
