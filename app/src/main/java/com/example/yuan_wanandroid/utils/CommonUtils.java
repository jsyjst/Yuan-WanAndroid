package com.example.yuan_wanandroid.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.Toast;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.contract.home.HomeFragmentContract;

import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 一些公有的工具
 * </pre>
 */


public class CommonUtils {

    public static void toastShow(String msg){
        Toast.makeText(App.getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    public static boolean isEmptyList(List<?> list) {
        return list == null || list.size() == 0;
    }


    public static void collectAnimator(Context context, View view){
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.collect_anim);
        animatorSet.setTarget(view);
        animatorSet.start();
    }

    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red =random.nextInt(150);
        //0-190
        int green =random.nextInt(150);
        //0-190
        int blue =random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green, blue);
    }

    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Constant.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Constant.TAB_COLORS[position];
    }

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
