package com.example.qinfen.MainActivity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.utils.LayoutAniMationUtils;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.ui.fastAdapter.CommonAdapter;
import com.goodsrc.qynglibrary.ui.fastAdapter.ViewHolder;

import java.util.ArrayList;

/**
 * 类介绍：会员管理的会员信息记录
 * 作者：suichengju on 2016/10/25 13:26
 * 邮箱：325927775@qq.com
 */
public class RecordActivity extends BaseActivity {

    private int tag;
    private ListView jilu_LV;
    private ArrayList<String> dates;
    private RecordActivity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordactivity);
        me = this;
        setTitle(getIntent().getStringExtra("title"));
        tag = getIntent().getIntExtra("tag", 0);
    }

    @Override
    protected void init() {
        jilu_LV = (ListView) findViewById(R.id.jilu_LV);
    }

    @Override
    protected void initset() {
        CommonAdapter<String> adapter = new CommonAdapter<String>(this, dates, R.layout.recordactivityitem) {
            @Override
            public void convert(ViewHolder holder, String s) {
                String a = "";
                String b = "";
                String c = "";
                String d = "";
                String e = "";
                if (tag == 1) {
                    a = s;
                    b = "类型：快速消费  ";
                    c = "店铺：默认店铺  ";
                    d = "消费金额：100.00  ";
                    e = "获得积分：0  ";
                } else if (tag == 2) {
                    a = s;
                    b = "充值金额：180.00  ";
                    c = "店铺：默认店铺  ";
                    d = "赠送金额：0  ";
                    e = "获得积分：0  ";
                } else if (tag == 3) {
                    a = s;
                    b = "冲次金额：80.00  ";
                    c = "店铺：默认店铺  ";
                    d = "获得积分：0  ";
                    e = "项目种类：4  ";
                } else if (tag == 4) {
                    holder.getView(R.id.imageView2).setVisibility(View.GONE);
                    a = s;
                    b = "来源：会员充值  ";
                    c = "店铺：默认店铺  ";
                    d = "获得积分：800  ";
                    e = "剩余积分：800  ";
                }
                holder.setText(R.id.cardnum_tv, a);
                holder.setText(R.id.tv_a, b);
                holder.setText(R.id.tv_b, c);
                holder.setText(R.id.tv_c, d);
                holder.setText(R.id.tv_d, e);
            }
        };
        jilu_LV.setLayoutAnimation(new LayoutAniMationUtils().getAnimationController());
        jilu_LV.setAdapter(adapter);
        jilu_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(me, RecordContentActivity.class);
                intent.putExtra("tag",tag);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadDatas() {
        dates = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dates.add("30647767" + i);
        }
    }
}
