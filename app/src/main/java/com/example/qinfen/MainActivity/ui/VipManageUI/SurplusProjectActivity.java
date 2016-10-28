package com.example.qinfen.MainActivity.ui.VipManageUI;

import android.os.Bundle;
import android.widget.ListView;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.utils.LayoutAniMationUtils;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.ui.fastAdapter.CommonAdapter;
import com.goodsrc.qynglibrary.ui.fastAdapter.ViewHolder;

import java.util.ArrayList;

/**
 * 类介绍：剩余项目
 * 作者：suichengju on 2016/10/25 10:01
 * 邮箱：325927775@qq.com
 */
public class SurplusProjectActivity extends BaseActivity {

    private ListView project_lv;
    private ArrayList<String> dates;
    private SurplusProjectActivity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surplusprojectactivity);
        me = this;
        setTitle("剩余项目");
        setBackEnable(true);
    }

    @Override
    protected void init() {
        project_lv = (ListView) findViewById(R.id.project_lv);
    }

    @Override
    protected void loadDatas() {
        dates = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dates.add("30647767" + i);
        }
    }

    @Override
    protected void initset() {
        CommonAdapter<String> adapter = new CommonAdapter<String>(this, dates, R.layout.suripluslvitem) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.name_tv, s);
            }
        };
        project_lv.setAdapter(adapter);
        project_lv.setLayoutAnimation(new LayoutAniMationUtils().getAnimationController());
    }


}
