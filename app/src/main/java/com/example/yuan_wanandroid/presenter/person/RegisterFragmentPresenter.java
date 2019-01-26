package com.example.yuan_wanandroid.presenter.person;

import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.contract.person.RegisterFragmentContract;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 注册
 * </pre>
 */


public class RegisterFragmentPresenter extends BasePresenter<RegisterFragmentContract.View>
        implements RegisterFragmentContract.Persenter  {

    @Inject
    public RegisterFragmentPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void register(String username, String password, String rePassword) {
        addRxSubscribe(
                mModel.register(username, password, rePassword)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponse>(mView,false,false){
                    @Override
                    public void onNext(BaseResponse response){
                        if(response.getErrorCode()==0){
                            mView.showSuccess();
                        }else{
                            mView.showToast(response.getErrorMsg());
                        }
                    }
                })
        );
    }
}
