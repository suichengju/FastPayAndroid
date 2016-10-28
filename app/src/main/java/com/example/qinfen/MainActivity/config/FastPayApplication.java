package com.example.qinfen.MainActivity.config;

import android.app.Application;
import android.content.Context;

import com.goodsrc.qynglibrary.core.LibraryApplication;
import com.goodsrc.qynglibrary.utils.MSPUtils;

import org.xutils.x;

/**
 * IM上下文环境
 * Created by suichengju on 2016/6/15.
 */
public class FastPayApplication {
    private static Context context;
    private static String Phone;
    private static String password;
    public final static String USER_ShouJiHao = "ShouJiHao";
    public final static String USER_PASS = "PassWord";
    public static MSPUtils sp = null;

    public static void init(Application application) {
        FastPayApplication.context = application;
        LibraryApplication.init(application);
        sp = new MSPUtils(application);
        x.Ext.init(application);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
    }

    public static String getPhone() {
        return sp.getString(USER_ShouJiHao);
    }

    public static void setPhone(String phone) {
        FastPayApplication.Phone = phone;
        sp.setString(USER_ShouJiHao, phone);
    }


    public static Context getContext() {
        return context;
    }

    public static void setPassword(String token) {
        FastPayApplication.password = token;
        LibraryApplication.setPhone(token);
    }

    public static String getPassword() {
        return sp.getString(Phone);
    }
}
