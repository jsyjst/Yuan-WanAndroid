package com.example.yuan_wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.GlideApp;


/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 图片工具类
 * </pre>
 */


public class ImageUtil {
    public static void loadImage(Context context, ImageView imageView, String path) {
        GlideApp.with(context)
                .load(path)
                .placeholder(R.drawable.test_jay)
                .error(R.drawable.test_jay)
                .into(imageView);
    }
}
