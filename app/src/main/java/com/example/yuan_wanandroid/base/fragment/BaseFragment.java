package com.example.yuan_wanandroid.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.utils.CommonUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : fragment的基类
 * </pre>
 */


public abstract class BaseFragment extends AbstractLazyLoadFragment implements BaseView {
    private Unbinder mBinder;
    protected Activity mActivity;
    protected abstract void inject();
    protected abstract void initView();//初始化控件
    protected abstract void loadData();//加载数据
    protected abstract int getLayoutId();//获取Fragment的布局Id

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mBinder = ButterKnife.bind(this, view);
        inject();
        initView();
        return view;
    }

    @Override
    protected void onLazyLoadData() {
        loadData();
    }

    @Override
    public void onDestroy() {
        if(mBinder != null && mBinder != Unbinder.EMPTY){
            mBinder.unbind();
        }
        super.onDestroy();
    }


    @Override
    public void setStatusBarColor() {

    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void reLoad() {

    }

    @Override
    public void showLoading() {

    }
    @Override
    public void showToast(String msg) {
        CommonUtils.toastShow(msg);
    }

    @Override
    public void showNightStyle(boolean isNight) {
    }

}
