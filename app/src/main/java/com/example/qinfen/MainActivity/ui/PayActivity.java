package com.example.qinfen.MainActivity.ui;

import android.os.Bundle;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.R;

/**
 * 类介绍：
 * 作者：suichengju on 2016/10/20 16:42
 * 邮箱：325927775@qq.com
 */
public class PayActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);
        setTitle("收款");
        setBackEnable(true);
    }

    @Override
    protected void init() {

    }
}
