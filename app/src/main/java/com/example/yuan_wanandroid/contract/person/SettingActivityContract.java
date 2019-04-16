package com.example.yuan_wanandroid.contract.person;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   : 设置接口集合
 * </pre>
 */

public interface SettingActivityContract {
    interface View extends BaseView{
        void showChangeNightStyle(); //显示夜间模式切换动画
    }
    interface Presenter extends IPresenter<View>{
        void setNightStyleState(boolean isNight); //设置夜间模式
        void setNoImgStyleState(boolean isNoImg); ///设置无图模式
        void setAutoCacheStyleState(boolean isAutoCache);//设置自动缓存模式
    }
}
