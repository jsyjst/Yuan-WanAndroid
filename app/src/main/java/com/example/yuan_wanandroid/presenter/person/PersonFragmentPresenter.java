package com.example.yuan_wanandroid.presenter.person;

import com.example.yuan_wanandroid.app.User;
import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.person.PersonFragmentContract;
import com.example.yuan_wanandroid.event.AutoRefreshEvent;
import com.example.yuan_wanandroid.event.LoginEvent;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   :
 * </pre>
 */


public class PersonFragmentPresenter extends BasePresenter<PersonFragmentContract.View>
        implements PersonFragmentContract.Presenter {

    @Inject
    public PersonFragmentPresenter(DataModel model) {
        super(model);
    }
    @Override
    public void subscribeEvent() {
        addRxSubscribe(
                RxBus.getInstance().toObservable(LoginEvent.class)
                        .filter(loginEvent -> loginEvent.isLogin())
                        .subscribe(loginEvent -> mView.showLogin())
        );
    }

    @Override
    public void logout() {
       addRxSubscribe(
               mModel.logout()
               .compose(RxUtil.rxSchedulerHelper())
               .subscribeWith(new BaseObserver<BaseResponse>(mView,false,false){
                   @Override
                   public void onNext(BaseResponse baseResponse){
                       User.getInstance().reset();
                       RxBus.getInstance().post(new AutoRefreshEvent(true));
                       mView.showLogout();
                   }
               })
       );
    }
}
