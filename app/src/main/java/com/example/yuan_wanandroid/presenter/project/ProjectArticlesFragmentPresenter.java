package com.example.yuan_wanandroid.presenter.project;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.project.ProjectArticlesFragmentContract;
import com.example.yuan_wanandroid.event.AutoRefreshEvent;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目文章
 * </pre>
 */


public class ProjectArticlesFragmentPresenter extends BasePresenter<ProjectArticlesFragmentContract.View>
        implements ProjectArticlesFragmentContract.Presenter {

    @Inject
    public ProjectArticlesFragmentPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void subscribeEvent() {
        addRxSubscribe(
                RxBus.getInstance().toObservable(AutoRefreshEvent.class)
                        .filter(autoRefreshEvent -> autoRefreshEvent.isAutoRefresh())
                        .subscribe(loginEvent -> mView.autoRefresh())
        );
    }

    @Override
    public void loadProjectArticlesData(int pageNum, int id) {
        addRxSubscribe(
                mModel.getProjectArticles(pageNum,id)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Articles>(mView){
                            @Override
                            public void onNext(Articles articles){
                                super.onNext(articles);
                                mView.showProjectArticles(articles.getDatas());
                            }
                        })
        );
    }

    @Override
    public void loadMoreProjectArticlesData(int pageNum, int id) {
        addRxSubscribe(
                mModel.getProjectArticles(pageNum,id)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<Articles>(mView,false,false){
                            @Override
                            public void onNext(Articles articles){
                                super.onNext(articles);
                                mView.showMoreProjectArticles(articles.getDatas());
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
