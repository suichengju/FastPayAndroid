package com.example.qinfen.MainActivity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.qinfen.MainActivity.utils.L;

/**
 * 基类
 *
 * @author suichengju
 *         created at 2016/10/19 14:40
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected abstract int getContentResid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResid());
        init();
        initset();
        loadDatas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i(getClass().getName());
    }

    /**
     * 初始化
     */
    protected void init() {

    }

    /**
     * 数据加载
     */
    protected void loadDatas() {

    }
    /**
     * 设置参数
     */
    protected void initset() {

    }

    /**
     * 带过场动画的启动activity方式
     *
     * @param intent
     * @param enterAnim
     * @param exitAnim
     */
    public void startActivity(Intent intent, int enterAnim, int exitAnim) {
        super.startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }
}
