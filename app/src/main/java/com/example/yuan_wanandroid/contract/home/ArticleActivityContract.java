package com.example.yuan_wanandroid.contract.home;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   :
 * </pre>
 */


public interface ArticleActivityContract {
    interface View extends BaseView {
        void showCollectSuccess(); //收藏成功
        void showUnCollectSuccess();//取消收藏成功
    }
    interface Presenter extends IPresenter<ArticleActivityContract.View>{
        void collectArticles(int id); //收藏首页文章
        void unCollectArticles(int id);//取消收藏
    }
}
