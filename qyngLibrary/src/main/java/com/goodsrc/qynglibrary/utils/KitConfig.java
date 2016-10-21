package com.goodsrc.qynglibrary.utils;

import java.util.HashMap;

/**
 * @author jinghq
 * @category 工具集框架配置管理
 * */
public class KitConfig {

	/** @category 内存静态对象 */
	public static KitConfig self = null;

	/** @category 日志开启开关 */
	public boolean isLog = true;

	/**
	 * @category 配置开关栈
	 *           <P>
	 *           注意，这里的配置为工具集配置，在内存中持久，并没有存盘，可能在低内存状态下会重置为默认参数
	 * */
	HashMap<CONFIG, Boolean> configStack = new HashMap<CONFIG, Boolean>();

	/**
	 * @category 配置参数栈
	 *           <P>
	 *           注意，这里的配置为工具集配置，在内存中持久，并没有存盘，可能在低内存状态下会重置为默认参数
	 * */
	HashMap<CONFIG, Object> valueStack = new HashMap<CONFIG, Object>();

	/**
	 * @category 单例模式
	 * */
	private KitConfig() {
		// 默认加载网络模块
		setConfig(CONFIG.HTTP_INIT, true);
		// 默认加载文件下载模块
		setConfig(CONFIG.FILELODER_INIT, true);

		setValue(CONFIG.HTTP_TIME_OUT, 60000);
		setValue(CONFIG.DEFAULT_SharedPreferences_NAME, "mstarcConfig");
		setValue(CONFIG.IMAGE_CACHE, "mstarc/file/pic");
		setValue(CONFIG.CHARSET_URL, "UTF-8");
		setValue(CONFIG.CHARSET_RESPONSE, "UTF-8");
		setValue(CONFIG.THREAD_COUNT, 5);
		setValue(CONFIG.FILE_CACHE, "mstarc/file");
		
		setConfig(CONFIG.ISLOG, true);
		// TODO 添加初始代码

	}

	/**
	 * @author jinghq
	 * @category 获取KitConfig的单例对象
	 * @return KitConfig
	 * */
	public static KitConfig getInstance() {
		if (self == null) {
			self = new KitConfig();
		}

		return self;
	}

	public static enum CONFIG {
		/** @category 是否加载HTTP模块 */
		HTTP_INIT,
		/** @category 是否加载文件下载模块 */
		FILELODER_INIT,
		/** @category 网络超时时间设置 */
		HTTP_TIME_OUT,
		/** @category 默认配置文件路径 */
		DEFAULT_SharedPreferences_NAME,
		/** @category 文件缓存文件夹 */
		FILE_CACHE,
		/** @category 图片缓存文件夹 */
		IMAGE_CACHE,
		/** @category URL编码 */
		CHARSET_URL,
		/** @category 返回数据编码 */
		CHARSET_RESPONSE,
		/** @category 返回线程池的最大线程数 */
		THREAD_COUNT,
		/** @category 是否输出日志 */
		ISLOG;
	}

	/**
	 * @category 获取框架配置
	 * @param key
	 *            设置项KEY
	 * */
	public boolean getConfig(CONFIG key) {
		return configStack.get(key);
	}

	/**
	 * @author jinghq
	 * @category 设置框架开关配置
	 * @param key
	 *            开关配置项key
	 * @param flag
	 *            开关设置项value
	 * */
	public void setConfig(CONFIG key, boolean flag) {
		configStack.put(key, flag);
	}

	/**
	 * @category 获取框架开关配置
	 * @param key
	 *            开关设置项KEY
	 * */
	public Object getValue(CONFIG key) {
		return valueStack.get(key);
	}

	/**
	 * @author jinghq
	 * @category 设置框架参数配置
	 * @param key
	 *            参数配置项key
	 * @param flag
	 *            参数设置项value
	 * */
	public void setValue(CONFIG key, Object value) {
		valueStack.put(key, value);
	}
}
