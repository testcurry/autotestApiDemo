package com.testcy.cases;

import com.testcy.bean.GetUserListCase;
import com.testcy.bean.InterfaceEnum;
import com.testcy.bean.LoginCase;
import com.testcy.config.TestConfig;
import com.testcy.mapper.LoginCaseMapper;
import com.testcy.utils.DBUtil;
import com.testcy.utils.GenerateUrlUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginCases {


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
        LoginCaseMapper mapper = sqlSession.getMapper(LoginCaseMapper.class);
        LoginCase loginCase = mapper.loginCase(1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
    }

    @Test(groups = "loginFalse",description = "登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DBUtil.getSqlSession();
        LoginCaseMapper mapper = sqlSession.getMapper(LoginCaseMapper.class);
        LoginCase loginCase = mapper.loginCase(2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
    }

}
