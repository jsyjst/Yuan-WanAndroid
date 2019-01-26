package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.person.PersonFragment;

import dagger.Provides;
import dagger.Subcomponent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 个人模块
 * </pre>
 */

@PerFragment
@Subcomponent()
public interface PersonFragmentComponent {
    void inject(PersonFragment personFragment);
}
