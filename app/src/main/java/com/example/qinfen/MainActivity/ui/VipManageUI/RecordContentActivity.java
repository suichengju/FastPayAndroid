package com.example.qinfen.MainActivity.ui.VipManageUI;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.widget.FastPayListItemView;
import com.example.qinfen.R;

/**
 * 类介绍：
 * 作者：suichengju on 2016/10/25 14:33
 * 邮箱：325927775@qq.com
 */
public class RecordContentActivity extends BaseActivity {

    private int tag;
    private LinearLayout LL_shangpinxiangqing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordcontentactivity);
        tag = getIntent().getIntExtra("tag", 0);
        if (tag == 1) {
            setTitle("快速消费");
        } else if (tag == 2) {
            setTitle("会员充值");
        } else if (tag == 3) {
            setTitle("会员冲次");
        }
        setBackEnable(true);
        inits();
    }

    protected void inits() {
        if (tag == 1) {
            LL_shangpinxiangqing = (LinearLayout) findViewById(R.id.LL_shangpinxiangqing);
            LL_shangpinxiangqing.setVisibility(View.VISIBLE);
            FastPayListItemView view = new FastPayListItemView(this);
            view.setName("1");
            view.setNumber("22222");
            FastPayListItemView view2 = new FastPayListItemView(this);
            view2.setName("1");
            view2.setNumber("22222");
            LL_shangpinxiangqing.addView(view);
            LL_shangpinxiangqing.addView(view2);
        }
    }
}
