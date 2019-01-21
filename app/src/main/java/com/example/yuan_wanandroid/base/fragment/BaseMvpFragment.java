package com.example.yuan_wanandroid.base.fragment;

import com.example.yuan_wanandroid.base.presenter.IPresenter;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 使用MVP的fragment基类
 * </pre>
 */


public abstract class BaseMvpFragment<T extends IPresenter> extends BaseFragment {

    protected abstract T getPresenter();
    protected T mPresenter;

    @Override
    protected void initView() {
        mPresenter = getPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
