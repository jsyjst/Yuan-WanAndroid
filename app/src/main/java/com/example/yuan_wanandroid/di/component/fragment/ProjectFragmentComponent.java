package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.module.fragment.ProjectFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.project.ProjectFragment;

import dagger.Subcomponent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   :
 * </pre>
 */

@PerFragment
@Subcomponent(modules = ProjectFragmentModule.class)
public interface ProjectFragmentComponent {
    void inject(ProjectFragment projectFragment);
}
