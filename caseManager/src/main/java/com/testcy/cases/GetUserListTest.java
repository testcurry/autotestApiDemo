package com.testcy.cases;

import com.testcy.bean.GetUserListCase;
import com.testcy.bean.User;
import com.testcy.config.TestConfig;
import com.testcy.mapper.CaseMapper;
import com.testcy.mapper.UserMapper;
import com.testcy.utils.DBUtil;
import netscape.javascript.JSObject;
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
import java.util.Arrays;
import java.util.List;

public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户列表信息")
    public void getUserList() throws IOException {

        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
        GetUserListCase getUserListCase = mapper.getUserListCase(1);
        System.out.println(TestConfig.getUserListUrl);
        System.out.println(getUserListCase.toString());

        //发送请求获取返回结果
        JSONArray resultJson = getJsonResult(getUserListCase);
        //验证返回结果
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserListByCondition(getUserListCase);
        for (User u : userList) {
            System.out.println(u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);
        Assert.assertEquals(userListJson.length(), resultJson.length());
        for (int i = 0; i < resultJson.length(); i++) {
            JSObject actualResult = (JSObject) resultJson.get(i);
            JSONObject expectedResult = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expectedResult.toString(), actualResult.toString());
        }
    }

    /**
     * HTTPClient封装请求，并发送请求获取请求结果
     *
     * @param getUserListCase
     * @return
     */
    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        //设置请求头
        post.setHeader("Content-Type", "application/json");
        //设置请求体
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", getUserListCase.getUserName());
        jsonObject.put("age", getUserListCase.getAge());
        jsonObject.put("sex", getUserListCase.getSex());
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(stringEntity);
        //设置Cookie信息
        TestConfig.client.setCookieStore(TestConfig.cookieStore);
        //发起请求并获取返回结果
        HttpResponse response = TestConfig.client.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        List<String> list = Arrays.asList(result);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray;
    }

}
