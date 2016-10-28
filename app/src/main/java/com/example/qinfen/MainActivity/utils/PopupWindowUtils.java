package com.example.qinfen.MainActivity.utils;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.qinfen.MainActivity.base.BaseActivity;

/**
 * Created by 32592 on 2016/7/21.
 */
public class PopupWindowUtils {
    private int resID;
    private View view;
    private PopupWindow popupWindow;
    private WindowManager.LayoutParams params;
    private Window window;
    private BaseActivity mcontext;

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public PopupWindowUtils(int resID, BaseActivity mcontext) {
        this.resID = resID;
        this.mcontext = mcontext;
    }
    public PopupWindowUtils(BaseActivity context){

    }


    public View getView() {
        view = mcontext.getLayoutInflater().inflate(resID, null);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (popupWindow.isShowing()){
                        popupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public PopupWindow getPoputWindow() {
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return popupWindow;
    }


}
