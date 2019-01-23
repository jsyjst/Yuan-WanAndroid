package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.module.fragment.SystemArticlesFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.system.SystemArticlesFragment;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   : 二级知识体系文章的注射器
 * </pre>
 */

@PerFragment
@Component(modules = SystemArticlesFragmentModule.class,dependencies = AppComponent.class)
public interface SystemArticlesFragmentComponent {
    void inject(SystemArticlesFragment systemArticlesFragment);
}
