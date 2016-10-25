package com.example.qinfen.MainActivity.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.qinfen.MainActivity.base.BaseActivity;
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
                initPopupWindow(R.layout.msg_menu, getRightView());
            }
        });
    }

    private void initPopupWindow(int msg_menu, ImageView view) {
        contentView = getLayoutInflater().inflate(msg_menu, null);
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
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.anim.activity_in_right);
        // 计算这个PopupWindow的显示位置
        int[] screen_pos = new int[2];
        view.getLocationOnScreen(screen_pos);
        Rect anchor_rect = new Rect(screen_pos[0], screen_pos[1], screen_pos[0] + view.getWidth(), screen_pos[1] + view.getHeight());
        contentView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int contentViewWidth = contentView.getMeasuredWidth();
        int position_x = anchor_rect.centerX() - (contentViewWidth / 2);
        int position_y = anchor_rect.bottom - (anchor_rect.height() / 2);
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
//        int dimension = (int) getResources().getDimension(R.dimen.activity_vertical_margins);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, position_x, position_y);
        params.alpha = 0.8f;
        getWindow().setAttributes(params);
    }

    @Override
    protected void init() {
        params = getWindow().getAttributes();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == surplusproject_tv) {
            intent = new Intent(me, SurplusProjectActivity.class);
            intent.putExtra("title", "剩余项目");
        } else if (v == xiaofei_TV) {//消费记录
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "消费记录");
            intent.putExtra("tag", 1);
        } else if (v == chongzhi_TV) {//充值记录
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "充值记录");
            intent.putExtra("tag", 2);
        } else if (v == chongci_TV) {//冲次记录
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "冲次记录");
            intent.putExtra("tag", 3);
        } else if (v == jifen_TV) {//积分纪录
            intent = new Intent(me, RecordActivity.class);
            intent.putExtra("title", "积分纪录");
            intent.putExtra("tag", 4);
        }
        startActivity(intent, R.anim.activity_in_right, R.anim.activity_out_left);
        popupWindow.dismiss();
        params.alpha = 1f;
        getWindow().setAttributes(params);
    }
}
