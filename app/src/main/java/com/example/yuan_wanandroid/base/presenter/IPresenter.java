package com.example.yuan_wanandroid.base.presenter;

import com.example.yuan_wanandroid.base.view.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   :
 * </pre>
 */


public interface IPresenter<T extends BaseView> {
    void attachView(T view);  //注入View
    boolean isAttachView();  //判断是否注入View
    void detachView();      //解除View

    //管理Rx的订阅事件
    void addRxSubscribe(Disposable disposable);
    //订阅事件
    void subscribeEvent();
    boolean getNightStyleState();//得到夜间模式的状态
    boolean getNoImgStyleState();//得到无图模式的状态
}
