package com.example.yuan_wanandroid.di.module.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.home.ArticlesAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.model.entity.Article;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 公众号Tab的实例提供
 * </pre>
 */


@Module
public class WxFragmentModule {
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
