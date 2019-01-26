package com.example.yuan_wanandroid.presenter.person;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.person.LoginFragmentContract;
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
                            mView.showLoginSuccess();
                        }else{
                            mView.showToast(baseResponse.getErrorMsg());
                        }
                    }
                })
        );
    }
}
