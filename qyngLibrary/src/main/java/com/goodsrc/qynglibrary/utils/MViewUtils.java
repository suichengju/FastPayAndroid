package com.goodsrc.qynglibrary.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author jinghq
 * @category 界面相关工具集
 * */
public class MViewUtils {

	/**
	 * @author jinghq
	 * @category 解析TextView中的超链接
	 * @param tv
	 *            要解析超链接的TextView对象
	 * */
	public static void AutoLink(TextView tv) {
		if (tv != null) {
			if (MTextUtils.notEmpty(tv.getText().toString())) {
				Linkify.addLinks(tv, Linkify.WEB_URLS);
			}
		}
	}

	/** @category 系统默认短Toast */
	public static void toastShort(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/** @category 系统默认长Toast */
	public static void toastLong(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	/**
	 * @author jinghq
	 * @category 屏幕宽度px 
	 * @param context
	 *            上下文
	 * */
	public static int GetScreenWidth(Context context) {
		return (int) context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * @author jinghq
	 * @category 屏幕高度px
	 * @param context
	 *            上下文
	 * */
	public static int GetScreenHeight(Context context) {
		return (int) context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * @author jinghq
	 * @category 隐藏一个View
	 * @param v
	 *            目标View
	 * */
	public static void hide(View v) {
		v.setVisibility(View.GONE);
	}

	/**
	 * @author jinghq
	 * @category 显示一个View
	 * @param v
	 *            目标View
	 * */
	public static void show(View v) {
		v.setVisibility(View.VISIBLE);
	}

	/**
	 * @author jinghq
	 * @category 给一个View设置资源背景
	 * @param View
	 *            目标View
	 * @param resId
	 *            资源ID
	 * */
	public static void bg(View v, int resId) {
		v.setBackgroundResource(resId);
	}

	/**
	 * @author JohnWell api 16以后自动调用 v.setBackgroud(drawAble)来实现;
	 * @author jinghq
	 * @category 给一个View设置背景
	 * @param v
	 *            目标View
	 * @param drawAble
	 *            背景Drawable对象
	 * */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressWarnings("deprecation")
	public static void bg(View v, Drawable drawAble) {
		if (MSysUtils.getSdkVersion() >= 16) {
			v.setBackground(drawAble);
		} else {
			v.setBackgroundDrawable(drawAble);
		}

	}

	/**
	 * @author jinghq
	 * @category 给一个View设置背景颜色
	 * @param v
	 *            目标View
	 * @param color
	 *            背景色值,16进制色值，如#000000
	 * */
	public static void bgColor(View v, String color) {
		bgColor(v, Color.parseColor(color));
	}

	/**
	 * @author jinghq
	 * @category 给一个View设置背景颜色
	 * @param v
	 *            目标View
	 * @param color
	 *            背景色值,int色值
	 * */
	public static void bgColor(View v, int color) {
		v.setBackgroundColor(color);
	}

	/**
	 * @category 获得repeat的图片drawable用来做repeat背景
	 * @author jinghq
	 * @param context
	 *            上下文对象
	 * @param drawable
	 *            资源id
	 * @return BitmapDrawable
	 * */
	public static BitmapDrawable getRepeatDrawable(Context context, int drawable) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                drawable);
		BitmapDrawable bd = new BitmapDrawable(new Resources(
				context.getAssets(),
				MSysUtils.getScreenDisplayMetrics(context), null), bitmap);
		bd.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
		return bd;
	}

	/**
	 * @author jinghq
	 * @category dip转px
	 * @param context
	 *            上下文
	 * @param dipValue
	 *            dip值
	 * */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * @author jinghq
	 * @category dip比例参数
	 * @param context
	 *            上下文
	 * */
	public static float GetScaleFloat(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}
	/**
	 * 控件获取焦点
	 * @param v
	 */
	public static void getFocus(View v){
		  v.setFocusable(true);
          v.setFocusableInTouchMode(true);
          v.requestFocus();
          v.requestFocusFromTouch();
	}
}
