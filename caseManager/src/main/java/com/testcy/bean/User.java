package com.testcy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", userName:'" + userName +
                ", password:'" + password +
                ", age:'" + age +
                ", sex:'" + sex +
                ", permission:'" + permission +
                ", isDelete:'" + isDelete +
                '}';
    }
}
