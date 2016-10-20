package com.example.qinfen.MainActivity.base;

import android.app.Application;

import com.goodsrc.qynglibrary.core.LibraryApplication;
import com.goodsrc.qynglibrary.utils.L;

import org.xutils.x;

/**
 * 应用级上下文对象
 * Created by ZhengChengwei on 2016/7/22.
 */
public class BaseApplication extends Application{
    public static boolean IS_DEVELOP = true;
    private static BaseApplication mApplication;

    public synchronized static BaseApplication getContext() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        x.Ext.init(this);
        if (IS_DEVELOP) {//开发模式
            x.Ext.setDebug(true); // 是否输出debug日志
            L.isDebug = IS_DEVELOP;
        }
        LibraryApplication.init(this);
//        if (currentUser == null) {
//            currentUser= new UserDBImpl().getCurrentUser();
//        }
    }


}
