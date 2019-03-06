package com.example.yuan_wanandroid.contract.person;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 我的模块
 * </pre>
 */


public interface PersonFragmentContract {
    interface View extends BaseView{
        void showLogin();
        void showLogout();
    }
    interface Presenter extends IPresenter<View>{
        void logout();
    }
}
