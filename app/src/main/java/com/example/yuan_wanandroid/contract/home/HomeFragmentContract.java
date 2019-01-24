package com.example.yuan_wanandroid.contract.home;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.model.entity.BannerData;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 首页的MVP接口集合
 * </pre>
 */


public interface HomeFragmentContract {
    interface View extends BaseView{
        void showBannerData(List<BannerData> bannerDataList); //展示轮播图
        void showArticles(List<Article> articlesList); //展示首页文章
        void showMoreArticles(List<Article> articleList);  //加载更多文章

    }


    interface Presenter extends IPresenter<HomeFragmentContract.View>{
        void loadBannerData();//加载首页banner数据
        void loadArticles(int pageNum);//加载首页文章数据
        void loadMoreArticles(int pageNum);//加载更多首页文章数据
    }
}
