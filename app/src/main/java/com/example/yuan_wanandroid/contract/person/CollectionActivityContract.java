package com.example.yuan_wanandroid.contract.person;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.Collection;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   : 收藏界面
 * </pre>
 */


public interface CollectionActivityContract {
    interface View extends BaseView{
        void showCollections(List<Collection> collections); //展示收藏列表
        void showMoreCollections(List<Collection> collections);  //加载更多收藏
        void showUnCollectSuccess();//取消收藏成功
        void initToolbar();
        void initRecyclerView();
        void initRefresh();
    }
    interface Presenter extends IPresenter<CollectionActivityContract.View>{
        void loadCollections(int pageNum);//加载收藏数据
        void loadMoreCollections(int pageNum);//加载更多收藏数据
        void unCollectArticles(int id,int originId);//取消收藏
    }
}
