package com.example.yuan_wanandroid.contract;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.model.entity.FirstSystem;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   : 知识体系模块下的MVP接口集合
 * </pre>
 */


public interface SystemFragmentContract {
    interface View extends BaseView{
        void showSystemData(List<FirstSystem> firstSystemList); //显示知识体系的目录
    }

    interface Presenter extends IPresenter<SystemFragmentContract.View>{
        void loadSystemData(); //加载知识体系的数据
    }
}
