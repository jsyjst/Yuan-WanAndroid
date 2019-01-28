package com.example.yuan_wanandroid.presenter.search;

import android.app.Activity;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.search.SearchArticlesActivityContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 搜索文章
 * </pre>
 */


public class SearchArticlesActivityPresenter extends BasePresenter<SearchArticlesActivityContract.View>
        implements SearchArticlesActivityContract.Presenter {

    @Inject
    public SearchArticlesActivityPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void loadArticles(int pageNum, String key) {
        addRxSubscribe(
                mModel.getSearchArticles(key,pageNum)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribeWith(new BaseObserver<Articles>(mView){
                    @Override
                    public void onNext(Articles articles){
                        super.onNext(articles);
                        mView.showArticles(articles.getDatas());
                    }
                })
        );
    }

    @Override
    public void loadMoreArticles(int pageNum, String key) {
        addRxSubscribe(
                mModel.getSearchArticles(key,pageNum)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Articles>(mView,false,false){
                            @Override
                            public void onNext(Articles articles){
                                super.onNext(articles);
                                mView.showMoreArticles(articles.getDatas());
                            }
                        })
        );
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
