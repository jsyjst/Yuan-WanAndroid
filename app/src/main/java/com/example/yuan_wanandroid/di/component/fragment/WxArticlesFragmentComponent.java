package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.module.fragment.WxArticlesFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.wx.WxArticlesFragment;

import dagger.Subcomponent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 公众号文章的注射器
 * </pre>
 */

@PerFragment
@Subcomponent(modules = WxArticlesFragmentModule.class)
public interface WxArticlesFragmentComponent {
    void inject(WxArticlesFragment wxArticlesFragment);
}
