package com.example.yuan_wanandroid.contract.project;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.contract.wx.WxFragmentContract;
import com.example.yuan_wanandroid.model.entity.Tab;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目模块的Mvp接口集合
 * </pre>
 */


public interface ProjectFragmentContract {
    interface View extends BaseView {
        void showProjectTab(List<Tab> tabList);
    }

    interface Presenter extends IPresenter<ProjectFragmentContract.View> {
        void loadProjectTabData();
    }
}
