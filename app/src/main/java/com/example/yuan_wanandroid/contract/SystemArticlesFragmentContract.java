package com.example.yuan_wanandroid.contract;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.Article;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   : 二级知识体系文章的MVP接口集合
 * </pre>
 */


public interface SystemArticlesFragmentContract {
    interface View extends BaseView{
        void showSystemArticles(List<Article> articlesList);//显示二级体系的文章
        void showMoreSystemArticles(List<Article> articlesList); //显示更多
    }


    interface Presenter extends IPresenter<SystemArticlesFragmentContract.View>{
        void loadSystemArticlesData(int pageNum,int id);//加载二级体系的文章
        void loadMoreSystemArticlesData(int pageNum,int id); //加载更多
    }
}
