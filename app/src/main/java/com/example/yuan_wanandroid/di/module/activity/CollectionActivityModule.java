package com.example.yuan_wanandroid.di.module.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.CollectionAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.di.scope.PerActivity;
import com.example.yuan_wanandroid.model.entity.Collection;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   : 收藏
 * </pre>
 */

@Module
public class CollectionActivityModule {
    @PerActivity
    @Provides
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(App.getContext());
    }

    @PerActivity
    @Provides
    List<Collection> provideCollectionList(){
        return new ArrayList<>();
    }

    @PerActivity
    @Provides
    CollectionAdapter provideCollectionAdapter(List<Collection> collections){
        return new CollectionAdapter(R.layout.item_home_list,collections);
    }
}
