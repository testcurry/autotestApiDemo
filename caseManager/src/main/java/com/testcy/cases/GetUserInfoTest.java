package com.testcy.cases;

import com.testcy.bean.GetUserInfoCase;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
        GetUserInfoCase getUserInfoCase = mapper.GetUserInfoCase(1);
        System.out.println(TestConfig.getUserInfoUrl);
        System.out.println(getUserInfoCase.toString());

        JSONArray resultJson=getJsonResult(getUserInfoCase);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user=userMapper.getUserInfo(getUserInfoCase);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        JSONArray expectedJson = new JSONArray(userList);
        JSONArray resultJson1 = new JSONArray(resultJson.getString(0));
        Assert.assertEquals(expectedJson.toString(), resultJson1.toString());
    }

    /**
     * HTTPClient封装请求信息，发送请求
     * @param getUserInfoCase
     * @return
     */
    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        post.setHeader("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getUserInfoCase.getUserId());

        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(stringEntity);
        TestConfig.client.setCookieStore(TestConfig.cookieStore);
        HttpResponse response = TestConfig.client.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
//        JSONArray jsonArray = new JSONArray(result);
        List<String> list = Arrays.asList(result);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray;
    }

}
