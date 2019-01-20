package com.example.yuan_wanandroid.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.component.ActivityCollector;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   : Activity的基类
 * </pre>
 */


public abstract class BaseActivity extends AppCompatActivity implements BaseView{
    private Unbinder mBinder;


    /**
     * 获取当前Activity布局的id
     * @return 布局id
     */
    protected abstract int getLayoutId();
    protected abstract void inject(); //注入
    protected abstract void initView(); //初始化布局
    protected abstract void initData(); //初始化数据

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        ActivityCollector.getInstance().addActivity(this);
        setStatusBarColor();
        inject();
        initView();
        initData();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if(mBinder != null && mBinder != mBinder.EMPTY){
            mBinder.unbind();
            mBinder = null;
        }
    }

}
