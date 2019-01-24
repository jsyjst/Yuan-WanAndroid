package com.example.yuan_wanandroid.contract.project;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.contract.wx.WxArticlesFragmentContract;
import com.example.yuan_wanandroid.model.entity.Article;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目文章Mvp接口集合
 * </pre>
 */


public interface ProjectArticlesFragmentContract {
    interface View extends BaseView {
        void showProjectArticles(List<Article> articleList);
        void showMoreProjectArticles(List<Article> articleList);
        void initRecyclerView();
        void initRefresh();
    }

    interface Presenter extends IPresenter<ProjectArticlesFragmentContract.View> {
        void loadProjectArticlesData(int pageNum,int id);
        void loadMoreProjectArticlesData(int pageNum,int id);
    }
}
