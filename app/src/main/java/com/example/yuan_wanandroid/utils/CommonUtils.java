package com.example.yuan_wanandroid.utils;

import android.widget.Toast;

import com.example.yuan_wanandroid.app.App;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 一些公有的工具
 * </pre>
 */


public class CommonUtils {

    public static void toastShow(String msg){
        Toast.makeText(App.getContext(),msg,Toast.LENGTH_SHORT);
    }
    public static boolean isEmptyList(List<?> list) {
        return list == null || list.size() == 0;
    }
}
