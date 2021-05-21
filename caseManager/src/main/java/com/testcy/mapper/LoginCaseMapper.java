package com.testcy.mapper;

import com.testcy.bean.LoginCase;
import com.testcy.cases.LoginCases;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginCaseMapper {

    public LoginCase loginCase(Integer id);

}
