package com.example.qinfen.MainActivity.ui;

import android.os.Bundle;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.R;

/**
 * 类介绍：
 * 作者：suichengju on 2016/10/25 14:33
 * 邮箱：325927775@qq.com
 */
public class RecordContentActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordcontentactivity);
        int tag = getIntent().getIntExtra("tag", 0);
        if(tag==1){
            setTitle("快速消费");
        }else if (tag==2){
            setTitle("会员充值");
        }else if (tag==3){
            setTitle("会员冲次");
        }
        setBackEnable(true);
    }
}
