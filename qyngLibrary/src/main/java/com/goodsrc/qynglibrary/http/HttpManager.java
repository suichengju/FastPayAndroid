package com.goodsrc.qynglibrary.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.goodsrc.qynglibrary.core.LibraryApplication;
import com.goodsrc.qynglibrary.utils.AppUtil;
import com.goodsrc.qynglibrary.utils.L;
import com.goodsrc.qynglibrary.utils.ModelUtil;
import com.goodsrc.qynglibrary.utils.NetworkUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Map;

/**
 * 网络请求的管理工具类
 * Created by ZhengChengwei on 2016/5/30.
 */
public class HttpManager {

    public static String CHART_GB2312 = "GB2312";
    public static String CHART_UTF8 = "UTF-8";

    /**
     * 上下文对象
     */
    private final Context context;
    /**
     * 链接超时时间
     */
    private int connectTimeOut;
    /**
     * 编码
     */
    private String textCharset;
    /**
     * 请求方式
     */
    private HttpMethod httpMethod;


    private  ProgressDialog progressDialog;
    private Callback.Cancelable cancelable;

    private HttpManager(Builder builder) {
        context = builder.context;
        connectTimeOut = builder.connectTimeOut;
        textCharset = builder.textCharset;
        httpMethod = builder.httpMethod;
    }

    /**
     * 构建器
     */
    public static class Builder{

        /**
         * 上下文对象
         */
        private Context context;
        /**
         * 链接超时时间
         */
        private int connectTimeOut=1000 * 20;
        /**
         * 编码
         */
        private String textCharset=CHART_UTF8;
        /**
         * 请求方式
         */
        private HttpMethod httpMethod= HttpMethod.POST;


        /**
         * 设置上下文对象
         * <p><strong>Note：</strong>指定context 显示进度，否则不显示
         * @param context 上下文对象
         */
        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        /**
         *设置 链接超时时间
         * <p><strong>Note：</strong>默认值20秒
         * @param connectTimeOut 链接超时时间
         */
        public Builder setConnectTimeOut(int connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
            return this;
        }

        /**
         * 设置请求编码
         * <p><strong>Note：</strong>包括请求参数编码和返回数据的编码，默认为
         * @param textCharset 编码
         */
        public Builder setTextCharset(String textCharset) {
            this.textCharset = textCharset;
            return this;
        }

        /**
         *设置请求方式
         * <p><strong>Note：</strong>默认为HttpRequest.HttpMethod.POST
         * @param httpMethod 请求方式
         */
        public Builder setHttpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }


        /**
         *
         * @return 请求对象
         */
        public HttpManager build() {
            return new HttpManager(this);
        }
    }

    /**
     * 网路请求
     *
     * @param params 参数
     * @param requestCallBack 回调接口
     * @return HttpHandler
     */
    public  <T> Callback.Cancelable request(final RequestParams params,
                                            final RequestCallBack<T> requestCallBack) {

        cancelable = http().request(httpMethod, params, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                L.d(params.toString());
                boolean networkConnected = NetworkUtil.isNetworkConnected(LibraryApplication.getContext());
                if (!networkConnected) {
                    cancelable.cancel();
                    requestCallBack.onError(null, "");
                } else {
                    requestCallBack.onStarted();
                    showProgress(context);
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                requestCallBack.onLoading(total, current, isDownloading);
            }

            @Override
            public void onSuccess(String result) {
                try {
                    L.d(result);
                    if (String.class != requestCallBack.mType) {
                        Object o = new Gson().fromJson(result, requestCallBack.mType);
                        if (o == null) {
                            requestCallBack.onError(null, "response null");
                            return;
                        }
                        requestCallBack.onSuccess((T) o);
                    } else {
                        requestCallBack.onSuccess((T) result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    requestCallBack.onError(e, e.getMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onError(new Exception(ex), "");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                requestCallBack.onCancelled();
            }

            @Override
            public void onFinished() {
                requestCallBack.onFinished();
                stopProgress();
            }
        });

        return cancelable;

    }

    /**
     * 下载文件
     * @param params 参数
     * @param requestCallBack 回调接口
     * @param savePath 保存路径
     * @param autoResume 是否断电续传
     * @return HttpHandler
     */
    public  <T> Callback.Cancelable downLoad(RequestParams params,String savePath,boolean autoResume,
                                            final RequestCallBack<T> requestCallBack) {

        params.setSaveFilePath(savePath);
        params.setAutoResume(autoResume);//断点续传
        cancelable = http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                boolean networkConnected = NetworkUtil.isNetworkConnected(LibraryApplication.getContext());
                if (!networkConnected) {
                    cancelable.cancel();
                    requestCallBack.onError(null, "");
                } else {
                    requestCallBack.onStarted();
                    showProgress(context);
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                requestCallBack.onLoading(total, current, isDownloading);
            }

            @Override
            public void onSuccess(File result) {
                try {
                    if (File.class == requestCallBack.mType) {
                        requestCallBack.onSuccess((T) result);
                    } else {
                        requestCallBack.onError(null, null);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    requestCallBack.onError(e, e.getMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onError(new Exception(ex), "");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                requestCallBack.onCancelled();
            }

            @Override
            public void onFinished() {
                requestCallBack.onFinished();
                stopProgress();
            }
        });

        return cancelable;
    }


    /**
     * 显示进度
     * @param context
     */
    private  void showProgress(Context context) {
        Activity activity = (Activity) context;
        if (context == null||activity.isFinishing()) {
            return;
        }

      /*  if (progressDialog != null && progressDialog.isShowing()&&progressDialog.getContext()!=context) {
            progressDialog.dismiss();
        }*/

        if (progressDialog == null||progressDialog.getContext()!=context) {
            progressDialog = ProgressDialog.show(context, null, "加载中···");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (cancelable != null && !cancelable.isCancelled()) {
                        cancelable.cancel();
                    }
                }
            });
        }



        progressDialog.show();
    }

    /**
     * 关闭进度
     */
    public  void stopProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }



    /**
     * 获取http请求对象
     *
     * @return HttpUtils
     */
    public  org.xutils.HttpManager http() {
        org.xutils.HttpManager httpManager = x.http();
        return httpManager;
    }

    /**
     * 获取参数
     * @param url 请求地址
     * @return
     */
    public  RequestParams params(String url) {
        RequestParams params = params(url, null);
        return params;
    }

    /**
     *获取参数
     * @param url 请求地址
     * @param model 要转为提交参数的实体类
     * @return
     */
    public  RequestParams params(String url,Object model) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(connectTimeOut);
        params.setCharset(textCharset);
        String token = LibraryApplication.getToken();
        if (!TextUtils.isEmpty(token)) {
            params.addBodyParameter("token", token);
        }
//        params.addBodyParameter("isAndroid","1");
//        params.addBodyParameter("version", AppUtil.getVersionCode(LibraryApplication.getContext())+"");
        if (model != null) {
            params = ModelUtil.RequestParamsReflect(model, params);
        }
        return params;
    }

    /**
     * 获取String链接
     * @param path 网络请求地址
     * @param map 参数
     * @return url地址
     */
    public static String getUrl(String path,Map<String, String> map) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(path);

        if (path.endsWith("?")) {
            urlBuilder.append("isAndroid=1");
        } else {
            urlBuilder.append("?isAndroid=1");
        }

        String token = LibraryApplication.getToken();
        if (!TextUtils.isEmpty(token)) {
            urlBuilder.append("&token=" + token);
        }

        urlBuilder.append("&version="+ AppUtil.getVersionCode(LibraryApplication.getContext()));
        if (map != null) {
            for (Map.Entry<String, String> param : map.entrySet()) {
                String key = param.getKey();
                String value = param.getValue();
                urlBuilder.append("&" + key + "=" + value);
            }
        }
        return urlBuilder.toString();
    }



}
