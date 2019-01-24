package com.example.yuan_wanandroid.contract.wx;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.Article;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 公众号文章接口
 * </pre>
 */


public interface WxArticlesFragmentContract {
    interface View extends BaseView{
        void showWxArticles(List<Article> articleList);
        void showMoreWxArticles(List<Article> articleList);
        void initRecyclerView();
        void initRefresh();
    }

    interface Presenter extends IPresenter<WxArticlesFragmentContract.View>{
        void loadWxArticlesData(int pageNum,int id);
        void loadMoreWxArticlesData(int pageNum,int id);
    }
}
