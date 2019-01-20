package com.example.yuan_wanandroid.base.view;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   : View接口
 * </pre>
 */


public interface BaseView {
    void showNormalView(); //显示正常布局
    void showErrorView();  //显示错误布局
    void reLoad(); //重新加载
    void showLoading();  //显示加载布局
    void showToast();  //显示Toast
    void setStatusBarColor();  //设置状态栏颜色
}
