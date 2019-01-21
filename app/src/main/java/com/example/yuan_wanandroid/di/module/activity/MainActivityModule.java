package com.example.yuan_wanandroid.di.module.activity;

import android.support.v4.app.Fragment;

import com.example.yuan_wanandroid.base.fragment.BaseFragment;
import com.example.yuan_wanandroid.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   :
 * </pre>
 */

@Module
public class MainActivityModule {

    @Provides
    @PerActivity
    Fragment[] provideFragments(){
        return new BaseFragment[5];
    }
}
