package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.module.fragment.HomeFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.home.HomeFragment;

import dagger.Subcomponent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : Home的注射器
 * </pre>
 */

@PerFragment
@Subcomponent(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {

    void inject(HomeFragment homeFragment);
}
