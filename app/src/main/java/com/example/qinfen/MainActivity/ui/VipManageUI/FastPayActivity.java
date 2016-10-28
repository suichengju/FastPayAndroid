package com.example.qinfen.MainActivity.ui.VipManageUI;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.utils.ToastUtil;

/**
 * 类介绍：
 * 作者：suichengju on 2016/10/20 14:33
 * 邮箱：325927775@qq.com
 */
public class FastPayActivity extends BaseActivity implements View.OnClickListener {

    private EditText moneyEt;
    private TextView vipPayBtn;
    private TextView payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fastpay_activity);
        String title = getIntent().getStringExtra("title");
        setBackEnable(true);
        setTitle(title);
    }

    @Override
    protected void initset() {

    }

    @Override
    protected void init() {
        moneyEt = (EditText) findViewById(R.id.money_et);
        vipPayBtn = (TextView) findViewById(R.id.vippay_btn);
        payBtn = (TextView) findViewById(R.id.pay_btn);
        vipPayBtn.setOnClickListener(this);
        payBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = moneyEt.getText().toString()+"";
        if (!text.equals("")) {
            double v1 = Double.parseDouble(text);
            if (v1 > 0) {
                if (v == payBtn) {//散客结账

                } else if (v == vipPayBtn) {//会员结账

                }
            }else {
                ToastUtil.showShort("支付金额必须大于0元");
            }
        }else {
            ToastUtil.showShort("请输入支付金额");
        }
    }
}
