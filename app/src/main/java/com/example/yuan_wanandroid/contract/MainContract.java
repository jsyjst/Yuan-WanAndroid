package com.example.yuan_wanandroid.contract;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   :
 * </pre>
 */

public interface MainContract {
    interface View extends BaseView{

    }

    interface Presenter extends IPresenter<View>{

    }
}
