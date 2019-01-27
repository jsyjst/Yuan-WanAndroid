package com.example.yuan_wanandroid.presenter.person;

import com.example.yuan_wanandroid.app.User;
import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.person.LoginFragmentContract;
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
 *     desc   : 登录
 * </pre>
 */


public class LoginFragmentPresenter extends BasePresenter<LoginFragmentContract.View>
        implements LoginFragmentContract.Presenter {

    @Inject
    public LoginFragmentPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void login(String username, String password) {
        addRxSubscribe(
                mModel.login(username, password)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponse>(mView,false,false){
                    @Override
                    public void onNext(BaseResponse baseResponse){
                        if(baseResponse.getErrorCode() == 0){
                            User user=User.getInstance();
                            user.setPassword(password);
                            user.setUsername(username);
                            user.setLoginStatus(true);
                            user.save();
                            RxBus.getInstance().post(new LoginEvent(true));
                            RxBus.getInstance().post(new AutoRefreshEvent(true));
                            mView.showLoginSuccess();
                        }else{
                            mView.showToast(baseResponse.getErrorMsg());
                        }
                    }
                })
        );
    }
}
