package com.testcy.cases;

import com.testcy.bean.AddUserCase;
import com.testcy.bean.User;
import com.testcy.config.TestConfig;
import com.testcy.mapper.CaseMapper;
import com.testcy.mapper.UserMapper;
import com.testcy.utils.DBUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "添加用户信息")
    public void addUser() throws IOException {

        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper caseMapper = sqlSession.getMapper(CaseMapper.class);
        AddUserCase addUserCase = caseMapper.addUserCase(1);
        System.out.println(TestConfig.addUserUrl);
        System.out.println(addUserCase.toString());

        //发请求
        String result=getResult(addUserCase);
        //验证请求结果
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addUser(addUserCase);
        Assert.assertEquals(addUserCase.getExpected(), result);
    }

    /**
     * httpClient封装请求信息，并获取返回结果
     * @param addUserCase
     * @return
     * @throws IOException
     */
    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post =new HttpPost(TestConfig.addUserUrl);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", addUserCase.getUserName());
        jsonObject.put("password", addUserCase.getPassword());
        jsonObject.put("sex", addUserCase.getSex());
        jsonObject.put("age", addUserCase.getAge());
        jsonObject.put("permission", addUserCase.getPermission());
        jsonObject.put("isDelete", addUserCase.getIsDelete());
        post.setHeader("Content-Type", "application/json");
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(stringEntity);
        //设置cookie信息
        TestConfig.client.setCookieStore(TestConfig.cookieStore);
        String result=null;
        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        return result;
    }

}
