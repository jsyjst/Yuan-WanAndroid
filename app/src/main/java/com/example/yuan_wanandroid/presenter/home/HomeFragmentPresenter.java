package com.example.yuan_wanandroid.presenter.home;

import android.util.Log;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.home.HomeFragmentContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BannerData;
import com.example.yuan_wanandroid.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   :
 * </pre>
 */


public class HomeFragmentPresenter extends BasePresenter<HomeFragmentContract.View>implements HomeFragmentContract.Presenter {
    private static final String TAG="HomeFragmentPresenter";

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
                        Log.d(TAG, "BannerOnNext: "+bannerData.size());
                        mView.showBannerData(bannerData);
                    }
                })
        );
    }

    @Override
    public void loadArticles(int pageNum) {
        addRxSubscribe(
                mModel.getArticles(pageNum)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Articles>(mView) {
                            @Override
                            public void onNext(Articles articles) {
                                super.onNext(articles);
                                mView.showArticles(articles.getDatas());
                            }
                        })
        );
    }

    @Override
    public void loadMoreArticles(int pageNum) {
        addRxSubscribe(
                mModel.getArticles(pageNum)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Articles>(mView,false,false) {
                            @Override
                            public void onNext(Articles articles) {
                                super.onNext(articles);
                                mView.showMoreArticles(articles.getDatas());
                            }
                        })
        );
    }

}
