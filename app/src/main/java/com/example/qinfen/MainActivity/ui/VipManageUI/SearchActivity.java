package com.example.qinfen.MainActivity.ui.VipManageUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qinfen.MainActivity.base.AbsBaseAdapter;
import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.utils.LayoutAniMationUtils;
import com.example.qinfen.MainActivity.widget.ClearEditText;
import com.example.qinfen.R;

import java.util.ArrayList;

/**
 * 类介绍：搜索会员界面
 * 作者：suichengju on 2016/10/24 16:04
 * 邮箱：325927775@qq.com
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private ListView lv_peron;
    private ArrayList<String> dates;
    private SearchActivity me;
    private ClearEditText et_search;
    private TextView tv_search;
    private AbsBaseAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBar();
        setContentView(R.layout.searchactivity);
    }

    @Override
    protected void init() {
        me = this;
        lv_peron = (ListView) findViewById(R.id.lv_peron);
        et_search = (ClearEditText) findViewById(R.id.et_search);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
    }

    @Override
    protected void loadDatas() {
        dates = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dates.add("30647767" + i);
        }
        adapter = new AbsBaseAdapter<String>(this, R.layout.vipuserlistitem) {
            @Override
            protected void bindDatas(ViewHolder viewHolder, String data, int position) {
                viewHolder.bindTextView(R.id.cardnum_tv, data);
            }
        };
        adapter.setDatas(dates);
        lv_peron.setLayoutAnimation(new LayoutAniMationUtils().getAnimationController());
    }

    @Override
    protected void initset() {
        lv_peron.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(me, VipUsetDetailsActivity.class);
                startActivity(intent);
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.equals("")) {
                    tv_search.setText("取消");
                } else {
                    tv_search.setText("搜索");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == tv_search) {
            String s = tv_search.getText().toString();
            if (s.equals("取消")) {
                me.finish();
            } else if (s.equals("搜索")) {
                lv_peron.setAdapter(adapter);
            }
        }
    }
}
