package com.example.yuan_wanandroid.presenter;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.HomeFragmentContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entiy.BannerData;
import com.example.yuan_wanandroid.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   :
 * </pre>
 */


public class HomeFragmentPresenter extends BasePresenter<HomeFragmentContract.View>implements HomeFragmentContract.Presenter {

    @Inject
    public HomeFragmentPresenter(DataModel model){
        super(model);
    }

    @Override
    public void loadBannerData() {
        addRxSubscribe(
                mModel.getBannerData()
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,false,false){
                    @Override
                    public void onNext(List<BannerData> bannerData) {
                        super.onNext(bannerData);
                        mView.showBannerData(bannerData);
                    }
                })
        );
    }

}
