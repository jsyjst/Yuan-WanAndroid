package com.example.yuan_wanandroid.app;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;


import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.component.DaggerAppComponent;
import com.example.yuan_wanandroid.di.module.AppModule;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;


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
    private RefWatcher mRefWatcher;//leakCanary内存泄漏监控

    //static代码段可以防止内存泄露
    //static代码段可以防止内存泄露
    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
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
        LitePal.initialize(this);
        initBugly();
        initLeakCanary();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //当应用所有UI隐藏时应该释放UI上所有占用的资源
        if(ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN == level)
            GlideApp.get(this).clearMemory();
        //根据level级别来清除一些图片缓存
        GlideApp.get(this).onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GlideApp.get(this).clearMemory();
    }

    public static App getContext() {
        return mApp;
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    //用于fragment的监控内存泄漏
    public static RefWatcher getRefWatcher(Context context) {
        App application =(App) context.getApplicationContext();
        return application.mRefWatcher;
    }

    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, Constant.BUGLY_APP_ID, false, strategy);

    }

    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher=LeakCanary.install(this);
    }
}
