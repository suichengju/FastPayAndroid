package com.example.qinfen.MainActivity.ui;

import android.os.Bundle;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.R;

/**
 * 类介绍：搜索会员界面
 * 作者：suichengju on 2016/10/24 16:04
 * 邮箱：325927775@qq.com
 */
public class SearchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBar();
        setContentView(R.layout.searchactivity);
    }
}
