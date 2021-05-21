package com.testcy.utils;

import com.testcy.bean.User;
import com.testcy.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "/",description = "这是我第一个版本的Demo")
public class DemoController {

    @Autowired
    private  SqlSessionFactory sqlSessionFactory;

/*    @Test
    public  void initSqlSession() throws IOException {
        String resource = "mybatis-conf.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer userCount = mapper.getUserCount();
        System.out.println(userCount);
    }*/

    @ApiOperation(value = "获取用户数量的接口",httpMethod = "GET")
    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    public int getUserCount(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Integer userCount;
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            userCount = mapper.getUserCount();
        } finally {
            sqlSession.close();
        }
        return userCount;
    }

    @ApiOperation(value = "添加用户的接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public User addUser(@RequestBody  User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.addUser(user);
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @ApiOperation(value = "更新用户的接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public User updateUser(@RequestBody  User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updateUser(user);
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @ApiOperation(value = "更新用户的接口",httpMethod = "Get")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public String updateUser(@RequestParam("id") Integer id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.deleteUser(id);
        } finally {
            sqlSession.close();
        }
        return id+"号记录被删除！";
    }


}
