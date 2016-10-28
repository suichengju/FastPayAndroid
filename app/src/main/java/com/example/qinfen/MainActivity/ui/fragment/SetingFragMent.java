package com.example.qinfen.MainActivity.ui.fragment;


import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.qinfen.MainActivity.base.BaseFragment;
import com.example.qinfen.MainActivity.config.FastPayApplication;
import com.example.qinfen.MainActivity.ui.LoginActivity;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.utils.ToastUtil;

/**
 * 设置主页面
 *
 * @author suichengju
 *         created at 2016/9/7 16:23
 */
public class SetingFragMent extends BaseFragment implements View.OnClickListener {


    private Button tuiChuBtn;
    private LinearLayout ll_xiugaimima;
    private LinearLayout ll_moreset;
    private LinearLayout ll_usehelp;
    private LinearLayout ll_shard;
    private LinearLayout ll_tools;
    private LinearLayout ll_guanyuwomen;
    private LinearLayout ll_update;
    private LinearLayout ll_fankui;
    private View contentView;
    private TextView tv_finish;
    private PopupWindow popupWindow;
    private WindowManager.LayoutParams params;
    private Window window;

    @Override
    protected int getContentResid() {

        return R.layout.setfragment;
    }

    @Override
    protected void init(View view) {
        window = getActivity().getWindow();
        params = window.getAttributes();
        tuiChuBtn = (Button) view.findViewById(R.id.btn_cancel);
        ll_xiugaimima = (LinearLayout) view.findViewById(R.id.ll_xiugaimima);
        ll_moreset = (LinearLayout) view.findViewById(R.id.ll_moreset);
        ll_usehelp = (LinearLayout) view.findViewById(R.id.ll_usehelp);
        ll_shard = (LinearLayout) view.findViewById(R.id.ll_shard);
        ll_tools = (LinearLayout) view.findViewById(R.id.ll_tools);
        ll_guanyuwomen = (LinearLayout) view.findViewById(R.id.ll_guanyuwomen);
        ll_update = (LinearLayout) view.findViewById(R.id.ll_update);
        ll_fankui = (LinearLayout) view.findViewById(R.id.ll_fankui);
        tuiChuBtn.setOnClickListener(this);
        ll_xiugaimima.setOnClickListener(this);
        ll_moreset.setOnClickListener(this);
        ll_usehelp.setOnClickListener(this);
        ll_shard.setOnClickListener(this);
        ll_tools.setOnClickListener(this);
        ll_guanyuwomen.setOnClickListener(this);
        ll_update.setOnClickListener(this);
        ll_fankui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tuiChuBtn) {//退出
            FastPayApplication.setPassword("");
            FastPayApplication.setPhone(null);
            ToastUtil.showShort("退出成功");
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        } else if (v == ll_xiugaimima) {//修改密码
            initPopupWindow(R.layout.popupwindow_mimachogngzhi, ll_xiugaimima, 1);
        } else if (v == ll_moreset) {//更多设置

        } else if (v == ll_usehelp) {//用户帮助

        } else if (v == ll_shard) {//推荐分享

        } else if (v == ll_tools) {//工具

        } else if (v == ll_guanyuwomen) {//关于我们

        } else if (v == ll_update) {//检测更新

        } else if (v == ll_fankui) {//反馈
            initPopupWindow(R.layout.popupwindow_yijian, ll_xiugaimima, 1);
        }
    }

    /**
     * 初始化所有的popupWindow
     *
     * @param msg_menu 布局id
     * @param view     父控件
     * @param type     是否需要有finish 0是不需要，1是需要
     */
    private void initPopupWindow(int msg_menu, View view, int type) {
        contentView = getActivity().getLayoutInflater().inflate(msg_menu, null);
        tv_finish = (TextView) contentView.findViewById(R.id.tv_finish);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.anim.activity_in_right);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);  //设置点击屏幕其它地方弹出框消失
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                window.setAttributes(params);
            }
        });
        if (msg_menu == R.layout.popupwindow_mimachogngzhi) {//修改密码
            EditText et_pass1 = (EditText) contentView.findViewById(R.id.et_pass1);
            EditText et_pass2 = (EditText) contentView.findViewById(R.id.et_pass2);
            TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (msg_menu == R.layout.popupwindow_yijian) {//意见反馈
            EditText et_pass1 = (EditText) contentView.findViewById(R.id.et_pass1);
            TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (msg_menu == R.layout.popupwindow_huanka) {
            EditText et_pass2 = (EditText) contentView.findViewById(R.id.et_pass2);
            TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (msg_menu == R.layout.popupwindow_suoding) {
            TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if (type == 1) {
            tv_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    params.alpha = 1f;
                    window.setAttributes(params);
                }
            });
        }
        params.alpha = 0.8f;
        window.setAttributes(params);
    }
}
