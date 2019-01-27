package com.example.yuan_wanandroid.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.contract.home.HomeFragmentContract;

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
}
