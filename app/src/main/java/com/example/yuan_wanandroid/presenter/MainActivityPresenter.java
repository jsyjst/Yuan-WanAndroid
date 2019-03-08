package com.example.yuan_wanandroid.presenter;

import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.MainContract;
import com.example.yuan_wanandroid.event.NightStyleEvent;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.utils.LogUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   :
 * </pre>
 */

public class MainActivityPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainActivityPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void subscribeEvent(){
        addRxSubscribe(
                RxBus.getInstance().toObservable(NightStyleEvent.class)
                        .subscribe(nightStyleEvent -> {
                            mView.showNightStyle(nightStyleEvent.isNight());
                        })
        );
    }
}
