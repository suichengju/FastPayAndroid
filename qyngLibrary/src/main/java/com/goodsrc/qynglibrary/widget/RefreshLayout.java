package com.goodsrc.qynglibrary.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 下拉刷新
 * Created by Goodsrc on 2016/4/27.
 */
public class RefreshLayout extends SwipeRefreshLayout {
    boolean Refresh = true;//是否开启下拉刷新

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_purple,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    private float mDownX;
    private float mDownY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = Math.abs((ev.getY() - mDownY));
                float dx = Math.abs((ev.getX() - mDownX));
                double z = Math.sqrt(dy * dy + dx * dx);
                int arg = Math.round((float) (Math.asin(dy / z) / Math.PI * 180));//最终角度
                if (arg <= 45 || !Refresh) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean isRefresh() {
        return Refresh;
    }

    public void setRefresh(boolean refresh) {
        Refresh = refresh;
    }
}
