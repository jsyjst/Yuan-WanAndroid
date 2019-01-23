package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.module.fragment.SystemFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.system.SystemFragment;

import dagger.Subcomponent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   :
 * </pre>
 */
@PerFragment
@Subcomponent(modules = SystemFragmentModule.class)
public interface SystemFragmentComponent {
    void inject(SystemFragment systemFragment);
}
