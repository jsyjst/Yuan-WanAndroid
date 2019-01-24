package com.example.yuan_wanandroid.di.component.activity;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.component.fragment.HomeFragmentComponent;
import com.example.yuan_wanandroid.di.component.fragment.SystemFragmentComponent;
import com.example.yuan_wanandroid.di.component.fragment.WxArticlesFragmentComponent;
import com.example.yuan_wanandroid.di.component.fragment.WxFragmentComponent;
import com.example.yuan_wanandroid.di.module.activity.MainActivityModule;
import com.example.yuan_wanandroid.di.module.fragment.HomeFragmentModule;
import com.example.yuan_wanandroid.di.module.fragment.SystemFragmentModule;
import com.example.yuan_wanandroid.di.module.fragment.WxArticlesFragmentModule;
import com.example.yuan_wanandroid.di.module.fragment.WxFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.view.MainActivity;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 主要作用是注射器，并提供给子fragment的AppComponent
 * </pre>
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    HomeFragmentComponent getHomeFragmentComponent(HomeFragmentModule homeFragmentModule);
    SystemFragmentComponent getSystemFragmentComponent(SystemFragmentModule systemFragmentModule);
    WxFragmentComponent getWxFragmentComponent(WxFragmentModule wxFragmentModule);
    WxArticlesFragmentComponent getWxArticlesFragmentComponent(WxArticlesFragmentModule wxArticlesFragmentModule);
}
