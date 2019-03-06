package com.example.yuan_wanandroid.di.component.activity;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.view.person.SettingActivity;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   : 设置注射器
 * </pre>
 */

@PerActivity
@Component(dependencies = AppComponent.class)
public interface SettingActivityComponent {
    void inject(SettingActivity settingActivity);
}
