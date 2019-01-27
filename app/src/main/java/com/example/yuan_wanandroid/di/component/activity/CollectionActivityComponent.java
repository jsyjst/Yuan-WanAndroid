package com.example.yuan_wanandroid.di.component.activity;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.module.activity.CollectionActivityModule;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.view.person.CollectionActivity;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   :
 * </pre>
 */


@PerActivity
@Component(dependencies = AppComponent.class,modules = CollectionActivityModule.class)
public interface CollectionActivityComponent {
    void inject(CollectionActivity collectionActivity);
}
