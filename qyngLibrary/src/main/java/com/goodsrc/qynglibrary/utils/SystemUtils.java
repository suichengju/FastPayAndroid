/*
 * @Title:  SystemUtils.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<请描述此文件是做什么的>
 * @author: JohnWell
 * @data:  2014-9-2 上午9:35:31
 * @version:  V1.0
 */
package com.goodsrc.qynglibrary.utils;

import android.content.Context;

/**
 * TODO<请描述这个类是干什么的>
 * @author  JohnWell
 * @data:  2014-9-2 上午9:35:31
 * @version:  V1.0
 */
public class SystemUtils {

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


}
