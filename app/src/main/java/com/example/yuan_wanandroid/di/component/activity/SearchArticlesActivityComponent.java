package com.example.yuan_wanandroid.di.component.activity;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.module.activity.SearchArticlesActivityModule;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.view.search.SearchArticlesActivity;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   :
 * </pre>
 */

@PerActivity
@Component(dependencies = AppComponent.class,modules = SearchArticlesActivityModule.class)
public interface SearchArticlesActivityComponent {
    void inject(SearchArticlesActivity searchArticlesActivity);
}
