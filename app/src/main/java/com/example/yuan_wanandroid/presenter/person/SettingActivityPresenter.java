package com.example.yuan_wanandroid.presenter.person;

import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.person.SettingActivityContract;
import com.example.yuan_wanandroid.event.NightStyleEvent;
import com.example.yuan_wanandroid.model.DataModel;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   : 设置
 * </pre>
 */

public class SettingActivityPresenter extends BasePresenter<SettingActivityContract.View>
        implements SettingActivityContract.Presenter  {

    @Inject
    public SettingActivityPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void subscribeEvent(){
        addRxSubscribe(
                RxBus.getInstance().toObservable(NightStyleEvent.class)
                        .subscribe(nightStyleEvent -> {
                            mView.changeNightStyle(nightStyleEvent.isNight());
                        })
        );
    }

    @Override
    public void setNightStyleState(boolean isNight) {
        mModel.setNightStyleState(isNight);
        RxBus.getInstance().post(new NightStyleEvent(isNight));
    }
}
