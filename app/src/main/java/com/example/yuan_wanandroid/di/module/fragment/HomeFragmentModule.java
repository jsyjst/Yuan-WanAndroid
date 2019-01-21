package com.example.yuan_wanandroid.di.module.fragment;

import com.example.yuan_wanandroid.di.scope.PerFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 首页的module
 * </pre>
 */

@Module
public class HomeFragmentModule {

    @Provides
    @PerFragment
    @Named("bannerTitles")
    List<String> provideBannerTitles(){
        return new ArrayList<>();
    }

    @Provides
    @PerFragment
    @Named("bannerImages")
    List<String> provideBannerImages(){
        return new ArrayList<>();
    }

}
