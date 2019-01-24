package com.example.yuan_wanandroid.di.component.fragment;

import com.example.yuan_wanandroid.di.module.fragment.ProjectArticlesFragmentModule;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.view.project.ProjectArticlesFragment;

import dagger.Subcomponent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   :
 * </pre>
 */

@PerFragment
@Subcomponent(modules = ProjectArticlesFragmentModule.class)
public interface ProjectArticlesFragmentComponent {
    void inject(ProjectArticlesFragment projectArticlesFragment);
}
