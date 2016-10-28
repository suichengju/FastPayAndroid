package com.example.qinfen.MainActivity.ui.VipManageUI;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.utils.PopupWindowUtils;
import com.example.qinfen.R;

/**
 * 类介绍：会员用户详情
 * 作者：suichengju on 2016/10/24 11:00
 * 邮箱：325927775@qq.com
 */
public class VipUsetDetailsActivity extends BaseActivity implements View.OnClickListener {

    private View contentView;
    private TextView chat;
    private PopupWindow popupWindow;
    private WindowManager.LayoutParams params;
    private TextView surplusproject_tv;
    private VipUsetDetailsActivity me;
    private TextView xiaofei_TV;
    private TextView chongzhi_TV;
    private TextView chongci_TV;
    private TextView jifen_TV;
    private TextView tv_chognzhimima;
    private TextView tv_huanka;
    private TextView tv_suoding;
    private PopupWindowUtils utils;
    private TextView tv_ok;
    private TextView tv_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vipuserdetails);
        me = this;
        setTitle("会员信息");
        setBackEnable(true);
        setIvAddVisible(true);
        setIvAddImage(R.drawable.msg_add);
        setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(R.layout.msg_menu, getRightView(), 0);
            }
        });
    }

    @Override
    protected void init() {
        utils = new PopupWindowUtils(me);
        params = getWindow().getAttributes();
        tv_chognzhimima = (TextView) findViewById(R.id.tv_chognzhimima);
        tv_huanka = (TextView) findViewById(R.id.tv_huanka);
        tv_suoding = (TextView) findViewById(R.id.tv_suoding);
        tv_chognzhimima.setOnClickListener(this);
        tv_huanka.setOnClickListener(this);
        tv_suoding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isstart = false;
        Intent intent = null;
        if (v == surplusproject_tv) {
            isstart = true;
            intent = new Intent(me, SurplusProjectActivity.class);
            intent.putExtra("title", "剩余项目");
        } else if (v == xiaofei_TV) {//消费记录
            isstart = true;
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "消费记录");
            intent.putExtra("tag", 1);
        } else if (v == chongzhi_TV) {//充值记录
            isstart = true;
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "充值记录");
            intent.putExtra("tag", 2);
        } else if (v == chongci_TV) {//冲次记录
            isstart = true;
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "冲次记录");
            intent.putExtra("tag", 3);
        } else if (v == jifen_TV) {//积分纪录
            isstart = true;
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "积分纪录");
            intent.putExtra("tag", 4);
        } else if (v == tv_chognzhimima) {//重置密码
            initPopupWindow(R.layout.popupwindow_chogngzhi, tv_chognzhimima, 1);
        } else if (v == tv_huanka) {//换卡
            initPopupWindow(R.layout.popupwindow_huanka, tv_huanka, 1);
        } else if (v == tv_suoding) {//锁定
            initPopupWindow(R.layout.popupwindow_suoding, tv_suoding, 1);
        }
        if (isstart) {
            startActivity(intent, R.anim.activity_in_right, R.anim.activity_out_left);
            popupWindow.dismiss();
            params.alpha = 1f;
            getWindow().setAttributes(params);
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
        contentView = getLayoutInflater().inflate(msg_menu, null);
        tv_finish = (TextView) contentView.findViewById(R.id.tv_finish);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.anim.activity_in_right);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);  //设置点击屏幕其它地方弹出框消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
        if (msg_menu == R.layout.msg_menu) {//更多详情
            surplusproject_tv = (TextView) contentView.findViewById(R.id.surplusproject_tv);
            xiaofei_TV = (TextView) contentView.findViewById(R.id.xiaofei_TV);
            chongzhi_TV = (TextView) contentView.findViewById(R.id.chongzhi_TV);
            chongci_TV = (TextView) contentView.findViewById(R.id.chongci_TV);
            jifen_TV = (TextView) contentView.findViewById(R.id.jifen_TV);
            surplusproject_tv.setOnClickListener(this);
            xiaofei_TV.setOnClickListener(this);
            chongzhi_TV.setOnClickListener(this);
            chongci_TV.setOnClickListener(this);
            jifen_TV.setOnClickListener(this);
            // 计算这个PopupWindow的显示位置
            int[] screen_pos = new int[2];
            view.getLocationOnScreen(screen_pos);
            Rect anchor_rect = new Rect(screen_pos[0], screen_pos[1], screen_pos[0] + view.getWidth(), screen_pos[1] + view.getHeight());
            contentView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int contentViewWidth = contentView.getMeasuredWidth();
            int position_x = anchor_rect.centerX() - (contentViewWidth / 2);
            int position_y = anchor_rect.bottom - (anchor_rect.height() / 2);
            popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, position_x, position_y);
        } else if (msg_menu == R.layout.popupwindow_chogngzhi) {//修改密码
            EditText et_pass1 = (EditText) contentView.findViewById(R.id.et_pass1);
            EditText et_pass2 = (EditText) contentView.findViewById(R.id.et_pass2);
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
                    getWindow().setAttributes(params);
                }
            });
        }
        params.alpha = 0.8f;
        getWindow().setAttributes(params);
    }
}
