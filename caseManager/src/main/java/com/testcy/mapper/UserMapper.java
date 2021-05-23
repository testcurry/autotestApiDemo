package com.testcy.mapper;

import com.testcy.bean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public void addUser(AddUserCase addUserCase);

    public List<User> getUserListByCondition(GetUserListCase getUserListCase);

    public User getUserInfo(GetUserInfoCase getUserInfoCase);

    public User getUpdateUserInfo(UpdateUserInfoCase updateUserInfoCase);
}
