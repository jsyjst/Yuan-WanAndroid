package com.example.yuan_wanandroid.contract.search;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.HotKey;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 搜索界面
 * </pre>
 */


public interface SearchActivityContract {
    interface View extends BaseView{
        void showSearchHotKeyFlow(List<HotKey> hotKeys); //显示热词布局
        void showHistoryData(List<String> historyList); //显示历史记录
        void showDeleteHistory(); //删除一条历史记录成功
        void showHistoriesEmptyView(); //显示历史记录为空的布局
        void hideHistoriesEmptyView();//隐藏历史记录为空的布局
        void showDeleteAllHistoriesSuccess(); //清空历史记录成功
        void toSearchArticlesActivity(String key);
        String getSearchEditText(); //得到搜索栏的关键字
    }
    interface Presenter extends IPresenter<SearchActivityContract.View>{
        void loadSearchHotKeyData();
        void loadHistoriesData(); //加载搜索历史记录
        void addHistory(String key); //添加历史记录
        void deleteHistory(String key); //删除历史记录
        void deleteAllHistories(); //删除所有历史记录
    }
}
