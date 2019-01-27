package com.example.yuan_wanandroid.presenter.person;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.person.CollectionActivityContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.Collection;
import com.example.yuan_wanandroid.model.entity.Collections;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   :
 * </pre>
 */


public class CollectionActivityPresenter extends BasePresenter<CollectionActivityContract.View>
        implements CollectionActivityContract.Presenter {

    @Inject
    public CollectionActivityPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void loadCollections(int pageNum) {
        addRxSubscribe(
                mModel.getCollectionsData(pageNum)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Collections>(mView) {
                            @Override
                            public void onNext(Collections collections) {
                                mView.showCollections(collections.getDatas());
                            }
                        })
        );
    }

    @Override
    public void loadMoreCollections(int pageNum) {
        addRxSubscribe(
                mModel.getCollectionsData(pageNum)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Collections>(mView) {
                            @Override
                            public void onNext(Collections collections) {
                                mView.showMoreCollections(collections.getDatas());
                            }
                        })
        );
    }

    @Override
    public void unCollectArticles(int id,int originId) {
        addRxSubscribe(
                mModel.unCollection(id,originId)
                        .compose(RxUtil.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<BaseResponse>(mView, false, false) {
                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                mView.showUnCollectSuccess();
                            }
                        })
        );
    }
}
