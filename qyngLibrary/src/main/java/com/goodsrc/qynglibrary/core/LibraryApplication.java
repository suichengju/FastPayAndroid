package com.goodsrc.qynglibrary.core;

import android.content.Context;

import com.goodsrc.qynglibrary.utils.MSPUtils;

/**
 *Library上下文环境
 * Created by ZhengChengwei on 2016/6/15.
 */
public class LibraryApplication {
    private static Context context;
    private static String token;
    public final static String USER_Token = "Token";
    public static MSPUtils sp = null;

    public static void init(Context context) {
        LibraryApplication.context = context;
        sp=new MSPUtils(context);
    }

    public static Context getContext() {
        return context;
    }

    public static void setToken(String token) {
        LibraryApplication.token = token;
        sp.setString(USER_Token, token);
    }

    public static String getToken() {
        return sp.getString(USER_Token);
    }
}
