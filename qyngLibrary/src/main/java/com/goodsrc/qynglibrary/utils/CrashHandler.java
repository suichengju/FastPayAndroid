package com.goodsrc.qynglibrary.utils;

import android.content.Context;
import android.os.Looper;
import android.text.format.Time;
import android.view.Gravity;
import android.widget.Toast;



import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

/**
 * @category 处理项目上线后崩溃，传递信息到服务器
 * @author Administrator
 *
 */
public class CrashHandler implements UncaughtExceptionHandler {

	/** Debug Log tag */
	public static final String TAG = "CrashHandler";
	/**
	 * 是否开启日志输出,在Debug状态下开启, 在Release状态下关闭以提示程序性能
	 * */
	public static final boolean DEBUG = KitConfig.getInstance().isLog;
	/** 系统默认的UncaughtException处理类 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	/** CrashHandler实例 */
	private static CrashHandler INSTANCE;
	/** 程序的Context对象 */
	private Context mContext;
	/** 使用Properties来保存设备的信息和错误堆栈信息 */
	private Properties mDeviceCrashInfo = new Properties();

	private static final String STACK_TRACE = "STACK_TRACE";
	/** 错误报告文件的扩展名 */
	private static final String CRASH_REPORTER_EXTENSION = ".cr";
	private OnPostReport onPostReport = null;

	/** 保证只有一个CrashHandler实例 */
	private CrashHandler() {
	}

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CrashHandler();
		}
		return INSTANCE;
	}

	/**
	 * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
	 * 
	 * @param ctx
	 */
	public void init(Context ctx) {
		mContext = ctx;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			SystemExit();
		}
	}

	/**
	 * / Sleep一会后结束程序
	 */
	public void SystemExit() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {

			return true;
		}
		final String msg = ex.getLocalizedMessage();
		if (msg == null) {
			return false;
		}
	
		// 使用Toast来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast toast = Toast.makeText(mContext, "非常抱歉,程序错误,我们会尽快解决:\r\n"
                        + "   错误信息:" + msg + "\r\n     正在发送错误...", Toast.LENGTH_LONG);
				
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

				Looper.loop();
			}
		}.start();
		// 收集崩溃的设备信息
		mDeviceCrashInfo.putAll(MSysUtils
				.CollectCrashDeviceInfo(mContext));
		// 保存错误报告文件
		saveCrashInfoToFile(ex);
		// 发送错误报告到服务器
		sendCrashReportsToServer(mContext);
		

		return true;
	}
	/**
	 * 在程序启动时候, 可以调用该函数来发送以前没有发送的报告
	 */
	public void sendPreviousReportsToServer() {

		sendCrashReportsToServer(mContext);
	}

	/**
	 * 把错误报告发送给服务器,包含新产生的和以前没发送的.
	 * 
	 * @param ctx
	 */
	private void sendCrashReportsToServer(Context ctx) {
		String[] crFiles = getCrashReportFiles(ctx);
		if (crFiles != null && crFiles.length > 0) {
			TreeSet<String> sortedFiles = new TreeSet<String>();
			sortedFiles.addAll(Arrays.asList(crFiles));

			List<File> crs=new ArrayList<File>();
			for (String fileName : sortedFiles) {


				File cr = new File(ctx.getFilesDir(), fileName);
				crs.add(cr);
				
				// cr.delete();// 删除已发送的报告
			}
			postReport(crs);
		}
	}

	/**
	 * 保存完文件之后的回调，可以在此做传送到服务器的操作
	 * 
	 * @paramfile
	 */
	protected void postReport(List<File> files) {
		if (onPostReport != null) {

			onPostReport.postReport(files);
		} else {

		}
	}

	/**
	 * 获取错误报告文件名
	 * 
	 * @param ctx
	 * @return
	 */
	private String[] getCrashReportFiles(Context ctx) {
		File filesDir = ctx.getFilesDir();
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(CRASH_REPORTER_EXTENSION);
			}
		};
		return filesDir.list(filter);
	}

	/**
	 * 保存错误信息到文件中
	 * 
	 * @param ex
	 * @return
	 */
	private String saveCrashInfoToFile(Throwable ex) {
		Writer info = new StringWriter();
		PrintWriter printWriter = new PrintWriter(info);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		String result = info.toString();
		printWriter.close();
		mDeviceCrashInfo.put("EXEPTION", ex.getLocalizedMessage());
		mDeviceCrashInfo.put(STACK_TRACE, result);
		try {
			// long timestamp = System.currentTimeMillis();
			Time t = new Time("GMT+8");
			t.setToNow(); // 取得系统时间
			int date = t.year * 10000 + t.month * 100 + t.monthDay;
			int time = t.hour * 10000 + t.minute * 100 + t.second;
			String fileName = "crash-" + date + "-" + time
					+ CRASH_REPORTER_EXTENSION;
			FileOutputStream trace = mContext.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			mDeviceCrashInfo.store(trace, "");
			trace.flush();
			trace.close();
			return fileName;
		} catch (Exception e) {

		}
		return null;
	}

	public interface OnPostReport {
		public void postReport(List<File> file);
	}

	public void RemoveOnPostReport() {
		this.onPostReport = null;
	}

	public void setOnPostReport(OnPostReport onPostReport) {
		this.onPostReport = onPostReport;
	}

}
