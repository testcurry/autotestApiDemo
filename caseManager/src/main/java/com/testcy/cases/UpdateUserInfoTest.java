package com.testcy.cases;

import com.testcy.bean.UpdateUserInfoCase;
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

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息userId=2，Username=hahahaha")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
        UpdateUserInfoCase updateUserInfoCase = mapper.updateUserInfoCase(1);
        System.out.println(TestConfig.updateUserInfoUrl);
        System.out.println(updateUserInfoCase.toString());

        int result =getResult(updateUserInfoCase);
//        Thread.sleep(5000);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUpdateUserInfo(updateUserInfoCase);
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);
    }



    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息userId=8")
    public void deleteUserInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
        UpdateUserInfoCase updateUserInfoCase = mapper.updateUserInfoCase(2);
        System.out.println(TestConfig.updateUserInfoUrl);
        System.out.println(updateUserInfoCase.toString());

        int result =getResult(updateUserInfoCase);
//        Thread.sleep(5000);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUpdateUserInfo(updateUserInfoCase);
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);
    }

    /**
     * HTTPClient封装请求。调用接口
     * @param updateUserInfoCase
     * @return
     */
    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        post.setHeader("Content-Type", "application/json");
        TestConfig.client.setCookieStore(TestConfig.cookieStore);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", updateUserInfoCase.getUserId());
        jsonObject.put("userName", updateUserInfoCase.getUserName());
        jsonObject.put("sex", updateUserInfoCase.getSex());
        jsonObject.put("age", updateUserInfoCase.getAge());
        jsonObject.put("permission", updateUserInfoCase.getPermission());
        jsonObject.put("isDelete", updateUserInfoCase.getIsDelete());

        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(stringEntity);
        HttpResponse response = TestConfig.client.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        return Integer.parseInt(result);
    }


}
