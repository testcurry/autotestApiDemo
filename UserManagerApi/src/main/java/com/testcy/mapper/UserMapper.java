package com.testcy.mapper;

import com.testcy.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public User queryUserByName(String username);


    public void addUser(User user);



    public int updateUserInfo(User user);

    List<User> getUserInfo(User user);
}
