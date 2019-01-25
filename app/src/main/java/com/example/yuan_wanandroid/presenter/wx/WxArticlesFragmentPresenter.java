package com.example.yuan_wanandroid.presenter.wx;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.wx.WxArticlesFragmentContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 公众号文章
 * </pre>
 */


public class WxArticlesFragmentPresenter extends BasePresenter<WxArticlesFragmentContract.View>
        implements WxArticlesFragmentContract.Presenter{

    @Inject
    public WxArticlesFragmentPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void loadWxArticlesData(int pageNum, int id) {
            addRxSubscribe(
                    mModel.getWxArticles(pageNum,id)
                    .compose(RxUtil.rxSchedulerHelper())
                    .compose(RxUtil.handleResult())
                    .subscribeWith(new BaseObserver<Articles>(mView){
                        @Override
                        public void onNext(Articles articles){
                            super.onNext(articles);
                            mView.showWxArticles(articles.getDatas());
                        }
                    })
            );
    }

    @Override
    public void loadMoreWxArticlesData(int pageNum, int id) {
        addRxSubscribe(
                mModel.getWxArticles(pageNum,id)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Articles>(mView,false,false){
                            @Override
                            public void onNext(Articles articles){
                                super.onNext(articles);
                                mView.showMoreWxArticles(articles.getDatas());
                            }
                        })
        );
    }
}
