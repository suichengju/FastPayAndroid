package com.example.qinfen.MainActivity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qinfen.R;
import com.goodsrc.qynglibrary.utils.L;

/**
 * 基类
 *
 * @author suichengju
 *         created at 2016/10/19 14:40
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected Toolbar toolbar;
    private View contentView;
    private LinearLayout rootView;
    private FrameLayout barLayout;
    private TextView titleTv;
    private TextView chongDianZhuang_TV;
    private ImageView iv_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        barLayout = (FrameLayout) rootView.findViewById(R.id.bar_layout);
        titleTv = (TextView) rootView.findViewById(R.id.title_tv);
        chongDianZhuang_TV = (TextView) rootView.findViewById(R.id.chongDianZhuang_TV);
        iv_add = (ImageView) rootView.findViewById(R.id.iv_add);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);
    }

    @Override
    public void setContentView(int layoutResID) {
        contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        rootView.addView(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        super.setContentView(rootView);
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


    /**
     * 隐藏toolbar
     */
    public void hideBar() {
        barLayout.setVisibility(View.GONE);
    }

    /**
     * 显示toolbar
     */
    public void showBar() {
        barLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置返回按钮是否可用
     *
     * @param backEnable
     */
    public void setBackEnable(boolean backEnable) {
        if (backEnable) {
            toolbar.setNavigationIcon(R.drawable.icon_back);
        } else {
            toolbar.setNavigationIcon(null);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        titleTv.setText(title);
    }


    @Override
    public void setTitle(int titleId) {
        String titleStr = getResources().getString(titleId);
        titleTv.setText(titleStr);
    }

    public void visbalChongDianZhuang(boolean isvisble) {
        if (isvisble) {
            chongDianZhuang_TV.setVisibility(View.VISIBLE);
        } else {
            chongDianZhuang_TV.setVisibility(View.GONE);
        }
    }

    /**
     * 控制加号的隐藏显示
     */
    public void setIvAddVisible(boolean isvisble) {
        if (isvisble) {
            iv_add.setVisibility(View.VISIBLE);
        } else {
            iv_add.setVisibility(View.GONE);
        }
    }

    public void setIvAddImage(int resid) {
        iv_add.setImageResource(resid);
    }

    public void setRightListener(View.OnClickListener l) {
        iv_add.setOnClickListener(l);
    }


    public TextView getChongDianZhuangView() {
        return chongDianZhuang_TV;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 判断用户是否登录
     */
    /*public boolean isLogin() {
        UserModel currentUser = BaseApplication.getCurrentUser();
        if (currentUser == null) {
            return false;
        } else {
            return true;
        }
    }*/

    /**
     * 跳转到登录页面
     */
   /* public void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }*/
}
