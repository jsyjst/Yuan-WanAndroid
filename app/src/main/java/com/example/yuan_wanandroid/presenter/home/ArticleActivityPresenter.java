package com.example.yuan_wanandroid.presenter.home;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.home.ArticleActivityContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   :
 * </pre>
 */


public class ArticleActivityPresenter extends BasePresenter<ArticleActivityContract.View>
        implements ArticleActivityContract.Presenter  {

    @Inject
    public ArticleActivityPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void collectArticles(int id) {
        addRxSubscribe(
                mModel.collectArticles(id)
                        .compose(RxUtil.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<BaseResponse>(mView,false,false){
                            @Override
                            public void onNext(BaseResponse baseResponse){
                                mView.showCollectSuccess();
                            }
                        })
        );
    }

    @Override
    public void unCollectArticles(int id) {
        addRxSubscribe(
                mModel.unCollectArticles(id)
                        .compose(RxUtil.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<BaseResponse>(mView,false,false){
                            @Override
                            public void onNext(BaseResponse baseResponse){
                                mView.showUnCollectSuccess();
                            }
                        })
        );
    }
}
