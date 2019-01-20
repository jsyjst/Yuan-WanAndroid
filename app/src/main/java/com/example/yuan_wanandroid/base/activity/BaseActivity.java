package com.example.yuan_wanandroid.base.activity;

import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   : Activity的基类
 * </pre>
 */


public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;
    protected abstract void inject(); //注入
    protected abstract void getLayoutId(); //得到活动的布局
    protected abstract void initView(); //初始化布局

}
