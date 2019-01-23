package com.example.yuan_wanandroid.di.module.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.system.FirstSystemAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.model.entity.FirstSystem;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   : 知识体系实例提供
 * </pre>
 */


@Module
public class SystemFragmentModule {
    @Provides
    @PerFragment
    List<FirstSystem> provideFirstSystemList(){
        return new ArrayList<>();
    }

    @Provides
    @PerFragment
    FirstSystemAdapter provideFirstSystemAdapter(List<FirstSystem> firstSystemList){
        return new FirstSystemAdapter(R.layout.item_system_list,firstSystemList);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(App.getContext());
    }
}
