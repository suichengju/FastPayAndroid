package com.goodsrc.qynglibrary.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.goodsrc.qynglibrary.R;

import org.xutils.ImageManager;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 图片加载工具类
 * Created by ZhengChengwei on 2016/6/22.
 */
public class ImageLoaderX {

    public static ImageOptions options(){
        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setRadius(DensityUtil.dip2px(5))
                .setLoadingDrawableId(R.drawable.image_loading)
                .setFailureDrawableId(R.drawable.image_loading).build();
        return imageOptions;
    }

    public static ImageManager image(){
        ImageManager image = x.image();
        return image;
    }


    /**
     * 绑定图片
     * @param imageView 要绑定的imageView 组件
     * @param url 图片的路径
     */
    public static void bind(ImageView imageView, String url){
        bind(imageView, url,options(),null);
    }
    /**
     * 绑定图片
     * @param imageView 要绑定的imageView 组件
     * @param url 图片的路径
     * @param commonCallback 图片回调
     */
    public static void bind(ImageView imageView, String url,Callback.CommonCallback<Drawable> commonCallback){
        bind(imageView, url,options(),commonCallback);
    }
    /**
     * 绑定图片
     * @param imageView 要绑定的imageView 组件
     * @param url 图片的路径
     * @param options 图片的设置
     */
    public static void bind(ImageView imageView, String url,ImageOptions options) {
        bind(imageView, url,options,null);
    }

    /**
     * 绑定图片
     * @param imageView 要绑定的imageView 组件
     * @param url 图片的路径
     * @param options 图片的设置
     * @param commonCallback 图片回调
     */
    public static void bind(ImageView imageView, String url,ImageOptions options , Callback.CommonCallback<Drawable> commonCallback){
        ImageManager imageManager = image();
        imageManager.bind(imageView, url, options, commonCallback);
    }

}
