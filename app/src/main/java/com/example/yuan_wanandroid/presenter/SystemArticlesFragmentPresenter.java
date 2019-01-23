package com.example.yuan_wanandroid.presenter;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.SystemArticlesFragmentContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.utils.RxUtil;
import com.example.yuan_wanandroid.view.system.SystemArticlesFragment;

import javax.inject.Inject;

import static com.example.yuan_wanandroid.utils.LogUtil.TAG_COMMON;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   :
 * </pre>
 */


public class SystemArticlesFragmentPresenter extends BasePresenter<SystemArticlesFragmentContract.View>
        implements SystemArticlesFragmentContract.Presenter{


    @Inject
    public SystemArticlesFragmentPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void loadSystemArticlesData(int pageNum, int id) {
        addRxSubscribe(
                mModel.getSecondSystemArticles(pageNum,id)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribeWith(new BaseObserver<Articles>(mView,false,false){
                    @Override
                    public void onNext(Articles articles){
                        LogUtil.d(LogUtil.TAG_COMMON,articles.getDatas().get(0).getTitle());
                        super.onNext(articles);
                        mView.showSystemArticles(articles.getDatas());
                    }
                })
        );
    }

    @Override
    public void loadMoreSystemArticlesData(int pageNum, int id) {
        addRxSubscribe(
                mModel.getSecondSystemArticles(pageNum,id)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Articles>(mView,false,false){
                            @Override
                            public void onNext(Articles articles){
                                super.onNext(articles);
                                mView.showMoreSystemArticles(articles.getDatas());
                            }
                        })
        );
    }
}
