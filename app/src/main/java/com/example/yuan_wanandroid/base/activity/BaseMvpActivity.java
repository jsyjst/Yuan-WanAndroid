package com.example.yuan_wanandroid.base.activity;

import android.os.Bundle;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.component.ActivityCollector;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   :
 * </pre>
 */


public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity {
    protected abstract T getPresenter();
    protected T mPresenter;

    @Override
    protected void initView(){
        mPresenter = getPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
