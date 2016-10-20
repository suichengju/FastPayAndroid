package com.example.qinfen.MainActivity.config;

import android.app.Application;
import android.content.Context;

import com.goodsrc.qynglibrary.core.LibraryApplication;
import com.goodsrc.qynglibrary.utils.MSPUtils;

import org.xutils.x;

/**
 * IM上下文环境
 * Created by ZhengChengwei on 2016/6/15.
 */
public class ParkApplication {
    private static Context context;
    private static String token;
    private static String phone;
    private static String deviceid;
    public final static String USER_ShouJiHao = "ShouJiHao";
    public final static String USER_Duuid = "Duuid";
    public final static String USER_Token = "Token";
    public static MSPUtils sp = null;

    public static void init(Application application) {
        ParkApplication.context = application;
        LibraryApplication.init(application);
        sp = new MSPUtils(application);
        x.Ext.init(application);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
    }

    public static String getPhone() {
        return sp.getString(USER_ShouJiHao);
    }

    public static void setPhone(String phone) {
        ParkApplication.phone = phone;
        sp.setString(USER_ShouJiHao, phone);
    }

    public static String getDeviceid() {
        return sp.getString(USER_Duuid);
    }

    public static void setDeviceid(String deviceid) {
        ParkApplication.deviceid = deviceid;
        sp.setString(USER_Duuid, deviceid);
    }

    public static Context getContext() {
        return context;
    }

    public static void setToken(String token) {
        ParkApplication.token = token;
        LibraryApplication.setToken(token);
    }

    public static String getToken() {
        return sp.getString(USER_Token);
    }
}
