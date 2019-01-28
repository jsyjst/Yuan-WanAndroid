package com.example.yuan_wanandroid.di.module.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.ArticlesAdapter;
import com.example.yuan_wanandroid.adapter.CollectionAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.Collection;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   :
 * </pre>
 */

@Module
public class SearchArticlesActivityModule {
    @PerActivity
    @Provides
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(App.getContext());
    }

    @PerActivity
    @Provides
    List<Article> provideCollectionList(){
        return new ArrayList<>();
    }

    @PerActivity
    @Provides
    ArticlesAdapter provideArticlesAdapter(List<Article> articles){
        return new ArticlesAdapter(R.layout.item_home_list,articles);
    }
}
