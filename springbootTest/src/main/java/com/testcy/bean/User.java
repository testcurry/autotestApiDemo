package com.testcy.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private String userName;
    private String password;
    private String age;
    private String sex;

}
