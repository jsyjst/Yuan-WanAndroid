package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.module.fragment.WxFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.wx.WxFragment;

import dagger.Subcomponent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 公众号Tab的注射器
 * </pre>
 */

@PerFragment
@Subcomponent(modules = WxFragmentModule.class)
public interface WxFragmentComponent {
    void inject(WxFragment wxFragment);
}
