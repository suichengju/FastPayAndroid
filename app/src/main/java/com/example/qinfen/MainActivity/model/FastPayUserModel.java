package com.example.qinfen.MainActivity.model;

import java.io.Serializable;

/**
 * 类介绍：
 * 作者：suichengju on 2016/10/20 10:50
 * 邮箱：325927775@qq.com
 */
public class FastPayUserModel implements Serializable{
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
