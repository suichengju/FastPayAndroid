package com.example.qinfen.MainActivity.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qinfen.R;

/**
 * 类介绍：自定义会员页item
 * 作者：suichengju on 2016/10/26 14:11
 * 邮箱：325927775@qq.com
 */
public class FastPayListItemView extends LinearLayout {

    Context context;
    private View view;
    private TextView nemeTV;
    private TextView numberTV;

    public FastPayListItemView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FastPayListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FastPayListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.fastpaylistitemview, this);
        numberTV = (TextView) view.findViewById(R.id.tv_number);
        nemeTV = (TextView) view.findViewById(R.id.tv_name);
    }

    public void setName(String name) {
        nemeTV.setText(name);
    }

    public void setNumber(String number) {
        numberTV.setText(number);
    }
}
