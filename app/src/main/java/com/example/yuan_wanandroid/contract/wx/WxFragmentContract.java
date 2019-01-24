package com.example.yuan_wanandroid.contract.wx;

import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.WxTab;

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
        void showWxTab(List<WxTab> wxTabList);
    }

    interface Presenter extends IPresenter<WxFragmentContract.View>{
        void loadWxTabData();
    }
}
