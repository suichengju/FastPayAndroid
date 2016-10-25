package com.example.qinfen.MainActivity.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

/**
 * 类介绍：listViewItem加载动画工具类
 * 作者：suichengju on 2016/10/25 11:04
 * 邮箱：325927775@qq.com
 */
public class LayoutAniMationUtils {

    private LayoutAnimationController animationController;

    public LayoutAnimationController getAnimationController() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(300);
        set.addAnimation(animation);
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.5f);
        return controller;
    }

    public void setAnimationController(LayoutAnimationController animationController) {
        this.animationController = animationController;
    }

}
