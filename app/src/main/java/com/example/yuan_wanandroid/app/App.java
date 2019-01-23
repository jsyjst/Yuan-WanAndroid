package com.example.yuan_wanandroid.app;

import android.app.Application;


import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.component.DaggerAppComponent;
import com.example.yuan_wanandroid.di.module.AppModule;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   :
 * </pre>
 */


public class App extends Application {
    private static App mApp;
    private static AppComponent mAppComponent;

    //static代码段可以防止内存泄露
    //static代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(((context, layout) -> {
            layout.setPrimaryColorsId(R.color.gray, R.color.colorNavNormal);//全局设置主题颜色
            return new ClassicsHeader(context); //经典Header
        }));
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(((context, layout) ->
                new ClassicsFooter(context).setDrawableSize(20))); //经典Footer
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static App getContext() {
        return mApp;
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
