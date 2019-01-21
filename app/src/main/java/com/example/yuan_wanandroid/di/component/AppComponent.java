package com.example.yuan_wanandroid.di.component;

import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.di.module.AppModule;
import com.example.yuan_wanandroid.model.DataModel;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   :
 * </pre>
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app); //注入自定义的application

    //暴露方法
    App getApp();
    DataModel getDataModel();
    Retrofit getRetrofit();

}
