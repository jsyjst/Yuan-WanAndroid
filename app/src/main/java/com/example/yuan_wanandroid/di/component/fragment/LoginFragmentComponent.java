package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.component.AppComponent;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.person.LoginFragment;

import dagger.Component;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 登录注射器
 * </pre>
 */

@PerFragment
@Component(dependencies = AppComponent.class)
public interface LoginFragmentComponent {
    void inject(LoginFragment loginFragment);
}
