package com.testcy.bean;

import lombok.Data;

@Data
public class AddUserCase {

    private Integer id;
    private String userName;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;

}
