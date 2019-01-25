package com.example.yuan_wanandroid.presenter.project;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.project.ProjectFragmentContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.Tab;
import com.example.yuan_wanandroid.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目Tab
 * </pre>
 */


public class ProjectFragmentPresenter extends BasePresenter<ProjectFragmentContract.View>
        implements ProjectFragmentContract.Presenter {
    @Inject
    public ProjectFragmentPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void loadProjectTabData() {
        addRxSubscribe(
                mModel.getProjectTab()
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleResult())
                        .subscribeWith(new BaseObserver<List<Tab>>(mView){
                            @Override
                            public void onNext(List<Tab> tabList){
                                super.onNext(tabList);
                                mView.showProjectTab(tabList);
                            }
                        })
        );
    }
}
