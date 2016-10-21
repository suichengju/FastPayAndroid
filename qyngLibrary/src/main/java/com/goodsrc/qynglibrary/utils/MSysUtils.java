package com.goodsrc.qynglibrary.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

public class MSysUtils {
	/** Debug Log tag */
	public static final String TAG = "MSysUtils";
	private static final String VERSION_NAME = "versionName";
	private static final String VERSION_CODE = "versionCode";

	/**
	 * @category 检查GPS设备是否可用
	 * @author jinghq
	 * */
	public static boolean isGpsEnable(Context context) {
		LocationManager locationManager = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * @category 使用系统发送短信
	 * @see 多号码使用；进行间隔 需要权限<uses-permission
	 *      android:name="android.permission.SEND_SMS"/>
	 * @param context
	 *            上下文对象
	 * @param number
	 *            电话号码，多号码用；进行间隔
	 * @param text
	 *            短信内容
	 * @author jinghq
	 * */
	public static void SendSMSBySystem(Context context, String number,
			String text) {
		Uri uri = Uri.parse("smsto:" + number);
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		it.putExtra("sms_body", text);
		context.startActivity(it);
	}

	/**
	 * 修改屏幕Density  主要用户减少内存，收集错误是使用
	 * @param context
	 */
	public static void ChangeMetrics(Context context) {
		DisplayMetrics curMetrics = context.getResources().getDisplayMetrics();
		if (!KitConfig.getInstance().isLog) {
			if (curMetrics.densityDpi == DisplayMetrics.DENSITY_HIGH) {
				DisplayMetrics metrics = new DisplayMetrics();
				metrics.scaledDensity = 1.0f;
				metrics.density = 1.0f;
				metrics.densityDpi = DisplayMetrics.DENSITY_MEDIUM;
				metrics.xdpi = DisplayMetrics.DENSITY_MEDIUM;
				metrics.ydpi = DisplayMetrics.DENSITY_MEDIUM;
				metrics.heightPixels = curMetrics.heightPixels;
				metrics.widthPixels = curMetrics.widthPixels;
				context.getResources().getDisplayMetrics().setTo(metrics);
			}
		} else {
			DisplayMetrics metrics = new DisplayMetrics();
			metrics.scaledDensity = (float) (130 / 160.0);
			metrics.density = (float) (130 / 160.0);
			metrics.densityDpi = 130;
			metrics.xdpi = 130;
			metrics.ydpi = 130;
			metrics.heightPixels = curMetrics.heightPixels;
			metrics.widthPixels = curMetrics.widthPixels;
			context.getResources().getDisplayMetrics().setTo(metrics);
		}
	}

	/**
	 * 收集程序设备信息 使用反射来收集设备信息.在Build类中包含各种设备信息 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
	 * 具体信息请自行查阅
	 * 
	 * @param ctx
	 */
	public static Properties CollectCrashDeviceInfo(Context ctx) {
		Properties mDeviceCrashInfo = new Properties();
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				mDeviceCrashInfo.put(VERSION_NAME,
						pi.versionName == null ? "not set" : pi.versionName);
				mDeviceCrashInfo.put(VERSION_CODE, "" + pi.versionCode);
			}
		} catch (NameNotFoundException e) {

		}

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				mDeviceCrashInfo.put(field.getName(), "" + field.get(null));
				if (KitConfig.getInstance().isLog) {

				}
			} catch (Exception e) {

			}
		}
		return mDeviceCrashInfo;
	}

	/**
	 * @category 使用系统打电话
	 * @see 多号码使用；进行间隔 需要权限<uses-permission
	 *      android:name="android.permission.CALL_PHONE"/>
	 * @param context
	 * @param number
	 *            电话号码
	 * 
	 */
	public static void CallSMSBySystem(Context context, String number) {
		Uri uri = Uri.parse("tel:" + number);
		Intent phoneIntent = new Intent("android.intent.action.CALL", uri);
		context.startActivity(phoneIntent);
	}

	/**
	 * @category 使用系统发送电子邮件
	 * @param context
	 *            上下文对象
	 * @param uri
	 *            邮箱地址
	 * @author jinghq
	 * */
	public static void SendEmail(Context context, String uri) {
		Intent data = new Intent(Intent.ACTION_SENDTO);
		data.setData(Uri.parse("mailto:" + uri));
		data.putExtra(Intent.EXTRA_SUBJECT, "");
		data.putExtra(Intent.EXTRA_TEXT, "");
		context.startActivity(data);
	}

	/**
	 * 返回设备SDCard路径,如果不存在则返回SDCardNotFoundException异常
	 * 
	 * @return
	 * @throws SDCardNotFoundException
	 */
	public static String getSDCardPath() throws SDCardNotFoundException {
		String sdDir = null;
		if (isSdCardExist()) {
			sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();// 获取跟目录
		} else {
			throw new SDCardNotFoundException();
		}
		return sdDir;

	}

	/**
	 * 判断sd卡是否存在
	 * 
	 * @return
	 */
	public static boolean isSdCardExist() {

		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取设备的sdk版本
	 * 
	 * @return
	 */
	public static int getSdkVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * 获得屏幕尺寸
	 * 
	 * @author m 此方法的使用需要sdk版本在11之上
	 * @param context
	 * @return
	 */
	@SuppressLint("NewApi")
	public static Point getScreenSize(Context context) {
		Point size = new Point();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getSize(size);
		// TODO 存在版本兼容问题
		return size;
	}

	/**
	 * 测量显示屏幕数据 metric.widthPixels; // 屏幕宽度（像素） metric.heightPixels; // 屏幕高度（像素）
	 * metric.density; // 屏幕密度（0.75 / 1.0 / 1.5） metric.densityDpi; //
	 * 屏幕密度DPI（120 / 160 / 240）
	 * 
	 * 需要注意的是，在一个低密度的小屏手机上 例如，一部240x320像素的低密度手机，如果运行上述代码， 获取到的屏幕尺寸是320x427。
	 * Android系统会将240x320的低密度（120）尺寸转换为中等密度（160）对应的尺寸，因而
	 * 需要在工程的AndroidManifest.xml文件中， 加入supports-screens节点，具体的内容如下：
	 * <supports-screens android:smallScreens="true"
	 * android:normalScreens="true" android:largeScreens="true"
	 * android:resizeable="true" android:anyDensity="true" />
	 * 
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getScreenDisplayMetrics(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metric);
		return metric;

	}

	/**
	 * metric.widthPixels; 屏幕宽度（像素）see getScreenDisplayMetrics
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWight(Context context) {
		return getScreenDisplayMetrics(context).widthPixels;
	}

	/**
	 * metric.heightPixels; 屏幕高度（像素）see getScreenDisplayMetrics
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		return getScreenDisplayMetrics(context).heightPixels;
	}

	/**
	 * @author jinghq
	 * @see 程序是否在前台运行
	 * @param context
	 *            上下文
	 * @return 在前台true 不在前台false
	 * */
	public static boolean isAppOnForeground(Context context) {
		// Returns a list of application processes that are running on the
		// device
		if (context == null)
			return false;
		ActivityManager activityManager = (ActivityManager) context
				.getApplicationContext().getSystemService(
						Context.ACTIVITY_SERVICE);
		String packageName = context.getApplicationContext().getPackageName();
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;
		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Role:Telecom service providers获取手机服务商信息 <BR>
	 * 需要加入权限
	 * <P>
	 * &lt;uses-permission android:name="android.permission.READ_PHONE_STATE"
	 * /&gt;
	 * 
	 * @author jinghq
	 * @category 获取手机服务商信息
	 * @param context
	 *            上下文
	 */
	public static String getProvidersName(Context context) {
		String ProvidersName = null;
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// 返回唯一的用户ID;就是这张卡的编号神马的
		String IMSI = telephonyManager.getSubscriberId();
		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		System.out.println(IMSI);
		if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
			ProvidersName = "中国移动";
		} else if (IMSI.startsWith("46001")) {
			ProvidersName = "中国联通";
		} else if (IMSI.startsWith("46003")) {
			ProvidersName = "中国电信";
		}
		return ProvidersName;
	}

	/**
	 * 返回手机型号版本
	 * 
	 * @return
	 */
	public static String getPhoneInfoMaition() {
		return android.os.Build.MODEL;
	}

	/**
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断wifi是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * @author jinghq
	 * @category 获得IMSI
	 * @param context
	 *            上下文
	 */
	public static String getIMSI(Context context) {
		if (context == null)
			return "";

		TelephonyManager mTelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = mTelephonyMgr.getSubscriberId();
		if (imsi == null) {
			imsi = "";
		}

		return imsi;
	}
	/**
	 * @author jinghq
	 * @category 获得IMSI
	 * @param context
	 *            上下文
	 */
	public static String getIMEI(Context context){
		if (context == null)
			return "";

		TelephonyManager mTelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = mTelephonyMgr.getDeviceId();
		if (imei == null) {
			imei = "";
		}

		return imei;
	}
}
