package com.testcy.mapper;

import com.testcy.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public Integer getUserCount();

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(Integer id);

}
