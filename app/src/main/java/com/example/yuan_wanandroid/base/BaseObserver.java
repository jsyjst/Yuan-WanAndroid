package com.example.yuan_wanandroid.base;

import android.net.ParseException;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.view.BaseView;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.UnknownHostException;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

import static com.example.yuan_wanandroid.utils.LogUtil.TAG_ERROR;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 对RxJava的下游即数据进行处理
 * </pre>
 */


public abstract class BaseObserver<T> extends ResourceObserver<T> {
    private BaseView mView;
    private boolean isShowErrorView = true;
    private boolean isShowProgress = true;
    private Disposable mDisposable;

    private BaseObserver() {
    }

    protected BaseObserver(BaseView view) {
        this(view, true, true);
    }

    protected BaseObserver(BaseView view, boolean isShowErrorView) {
        this(view, isShowErrorView, true);
    }

    protected BaseObserver(BaseView view, boolean isShowErrorView, boolean isShowProgress) {
        mView = view;
        this.isShowErrorView = isShowErrorView;
        this.isShowProgress = isShowProgress;
    }

    @Override
    protected void onStart() {
        if (isShowProgress) mView.showLoading();
    }

    @Override
    public void onNext(T t) {
        mView.showNormalView();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof UnknownHostException) {
            LogUtil.e(TAG_ERROR, "networkError：" + e.getMessage());
            networkError();
        } else if (e instanceof InterruptedException) {
            LogUtil.e(TAG_ERROR, "timeout：" + e.getMessage());
            timeoutError();
        } else if (e instanceof HttpException) {
            LogUtil.e(TAG_ERROR, "http错误：" + e.getMessage());
            httpError();
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            LogUtil.e(TAG_ERROR, "解析错误：" + e.getMessage());
            parseError();
        } else {
            LogUtil.e(TAG_ERROR, "unknown：" + e.getMessage());
            unknown();
        }
    }

    @Override
    public void onComplete(){
    }

    /**
     * 未知错误
     */
    protected void unknown() {
        mView.showToast(App.getContext().getString(R.string.error_unknown));
        if (isShowErrorView) mView.showErrorView();
    }

    /**
     * 解析错误
     */
    protected void parseError() {
        if (isShowErrorView) mView.showErrorView();
    }

    /**
     * http错误
     */
    protected void httpError() {
        mView.showToast(App.getContext().getString(R.string.error_http));
        if (isShowErrorView) mView.showErrorView();
    }

    /**
     * 网络超时异常
     */
    protected void timeoutError() {
        mView.showToast(App.getContext().getString(R.string.error_timeout));
        if (isShowErrorView) mView.showErrorView();
    }

    /**
     * 网络不可用异常
     */
    protected void networkError() {
        mView.showToast(App.getContext().getString(R.string.error_network));
        if (isShowErrorView) mView.showErrorView();
    }


}
