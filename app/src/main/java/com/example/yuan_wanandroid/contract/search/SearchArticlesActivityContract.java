package com.example.yuan_wanandroid.contract.search;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.model.entity.Collection;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 搜索文章
 * </pre>
 */


public interface SearchArticlesActivityContract {
    interface View extends BaseView {
        void showArticles(List<Article> articles);
        void showMoreArticles(List<Article> articles);
        void showCollectSuccess();
        void showUnCollectSuccess();
        void initToolbar();
        void initRecyclerView();
        void initRefresh();
    }
    interface Presenter extends IPresenter<SearchArticlesActivityContract.View>{
        void loadArticles(int pageNum,String key);
        void loadMoreArticles(int pageNum,String key);
        void collectArticles(int id);
        void unCollectArticles(int id);
    }
}
