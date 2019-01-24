package com.example.yuan_wanandroid.presenter.wx;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.wx.WxFragmentContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.WxTab;
import com.example.yuan_wanandroid.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 公众号Tab
 * </pre>
 */


public class WxFragmentPresenter extends BasePresenter<WxFragmentContract.View>
        implements WxFragmentContract.Presenter {

    @Inject
    public WxFragmentPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void loadWxTabData() {
        addRxSubscribe(
                mModel.getWxTabs()
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribeWith(new BaseObserver<List<WxTab>>(mView,false,false){
                    @Override
                    public void onNext(List<WxTab> wxTabList){
                        super.onNext(wxTabList);
                        mView.showWxTab(wxTabList);
                    }
                })
        );
    }
}
