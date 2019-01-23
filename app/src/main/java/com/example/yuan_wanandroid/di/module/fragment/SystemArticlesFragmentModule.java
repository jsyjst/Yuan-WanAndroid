package com.example.yuan_wanandroid.di.module.fragment;

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
 *     time   : 2019/01/23
 *     desc   : 二级知识体系文章
 * </pre>
 */

@Module
public class SystemArticlesFragmentModule {
    @Provides
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(App.getContext());
    }


    @Provides
    @PerFragment
    List<Article> provideArticles(){
        return new ArrayList<>();
    }

    @Provides
    @PerFragment
    ArticlesAdapter provideArticlesAdapter(List<Article> articles){
        return new ArticlesAdapter(R.layout.item_home_list, articles);
    }
}
