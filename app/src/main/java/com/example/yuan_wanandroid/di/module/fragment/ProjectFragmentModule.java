package com.example.yuan_wanandroid.di.module.fragment;

import android.support.v4.app.Fragment;

import com.example.yuan_wanandroid.di.scope.PerFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目Tab
 * </pre>
 */

@Module
public class ProjectFragmentModule {
    @Provides
    @PerFragment
    List<Fragment> provideSupportFragment(){
        return new ArrayList<>();
    }

    @Provides
    @PerFragment
    List<String> provideTitles(){
        return new ArrayList<>();
    }

    @Provides
    @PerFragment
    List<Integer> provideIds(){
        return new ArrayList<>();
    }
}
