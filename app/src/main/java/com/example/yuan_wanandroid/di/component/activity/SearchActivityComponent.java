package com.example.yuan_wanandroid.di.component.activity;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.view.search.SearchActivity;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 搜索关键词
 * </pre>
 */

@PerActivity
@Component(dependencies = AppComponent.class)
public interface SearchActivityComponent {
    void inject(SearchActivity searchActivity);
}
