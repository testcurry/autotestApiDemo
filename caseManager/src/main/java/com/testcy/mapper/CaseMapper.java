package com.testcy.mapper;

import com.testcy.bean.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaseMapper {

    public LoginCase loginCase(Integer id);

    public AddUserCase addUserCase(Integer id);

    public GetUserListCase getUserListCase(Integer id);

    public GetUserInfoCase GetUserInfoCase(Integer id);

    public UpdateUserInfoCase updateUserInfoCase(Integer id);


}
