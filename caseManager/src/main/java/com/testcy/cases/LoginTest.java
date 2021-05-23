package com.testcy.cases;

import com.testcy.bean.InterfaceEnum;
import com.testcy.bean.LoginCase;
import com.testcy.config.TestConfig;
import com.testcy.mapper.CaseMapper;
import com.testcy.utils.DBUtil;
import com.testcy.utils.GenerateUrlUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {


    @BeforeTest(groups = "login",description = "初始化工作")
    public void init() {
        TestConfig.getUserInfoUrl= GenerateUrlUtil.getUrl(InterfaceEnum.GetUserInfoCase);
        TestConfig.addUserUrl=GenerateUrlUtil.getUrl(InterfaceEnum.AddUserCase);
        TestConfig.loginUrl=GenerateUrlUtil.getUrl(InterfaceEnum.LoginCase);
        TestConfig.getUserListUrl= GenerateUrlUtil.getUrl(InterfaceEnum.GetUserListCase);
        TestConfig.updateUserInfoUrl=GenerateUrlUtil.getUrl(InterfaceEnum.UpdateUserInfoCase);
        TestConfig.client=new DefaultHttpClient();
    }

    @Test(groups = "loginTrue",description = "登录成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
        LoginCase loginCase = mapper.loginCase(1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求
        String result=getResult(loginCase);
        //验证返回结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }



    @Test(groups = "loginFalse",description = "登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
        LoginCase loginCase = mapper.loginCase(2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求
        String result=getResult(loginCase);
        //验证返回结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }

    /**
     * httpClient封装请求信息，并获取返回结果
     * @param loginCase
     * @return
     * @throws IOException
     */
    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", loginCase.getUserName());
        jsonObject.put("password", loginCase.getPassword());
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        //设置json格式的请求体body
        post.setEntity(stringEntity);
        //设置请求头
        post.setHeader("Content-Type", "application/json");
        //发送请求
        HttpResponse response = TestConfig.client.execute(post);
        HttpEntity entity = response.getEntity();
        String result= EntityUtils.toString(entity,"utf-8");
        TestConfig.cookieStore=TestConfig.client.getCookieStore();
        return result;
    }

}
