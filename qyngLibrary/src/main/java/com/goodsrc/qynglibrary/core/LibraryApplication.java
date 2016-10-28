package com.goodsrc.qynglibrary.core;

import android.content.Context;

import com.goodsrc.qynglibrary.utils.MSPUtils;

/**
 *Library上下文环境
 * Created by suichengju on 2016/6/15.
 */
public class LibraryApplication {
    private static Context context;
    private static String Phone;
    public final static String PHONE = "Phone";
    public static MSPUtils sp = null;

    public static void init(Context context) {
        LibraryApplication.context = context;
        sp=new MSPUtils(context);
    }

    public static Context getContext() {
        return context;
    }

    public static void setPhone(String phone) {
        LibraryApplication.Phone = phone;
        sp.setString(PHONE, phone);
    }

    public static String getToken() {
        return sp.getString(PHONE);
    }
}
