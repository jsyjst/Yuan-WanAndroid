package com.example.yuan_wanandroid.di.component.activity;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.view.home.ArticleActivity;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   :
 * </pre>
 */

@PerActivity
@Component(dependencies = AppComponent.class)
public interface ArticleActivityComponent {
    void inject(ArticleActivity articleActivity);
}
