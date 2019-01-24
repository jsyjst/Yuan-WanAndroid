package com.example.yuan_wanandroid.contract.wx;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.Tab;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 微信公众号Tab的Mvp接口集合
 * </pre>
 */


public interface WxFragmentContract {
    interface View extends BaseView{
        void showWxTab(List<Tab> tabList);
    }

    interface Presenter extends IPresenter<WxFragmentContract.View>{
        void loadWxTabData();
    }
}
