package com.example.yuan_wanandroid.app;

import android.app.Application;


import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.component.DaggerAppComponent;
import com.example.yuan_wanandroid.di.module.AppModule;


/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   :
 * </pre>
 */


public class App extends Application {
    private static App mApp;
    private AppComponent mAppComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        mApp = this;
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static App getContext(){
        return mApp;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
