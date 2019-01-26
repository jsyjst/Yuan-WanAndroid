package com.example.yuan_wanandroid.utils;

import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.http.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 对RxJava的封装
 * </pre>
 */


public class RxUtil {
    private RxUtil() {
        throw new AssertionError();
    }

    /**
     * 切换线程
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 对结果进行预处理
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return upstream ->
                upstream.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
                    if (baseResponse.getErrorCode() == 0) {
                        return createObservable(baseResponse.getData());//创建我们需要的数据
                    }
                    return Observable.error(
                            new ApiException(baseResponse.getErrorCode(), baseResponse.getErrorMsg())//创建一个异常
                    );
                });
    }


    /**
     * 创建成功的数据源
     */
    private static <T> Observable<T> createObservable(T data) {

        return Observable.create(emitter -> {
            try {
                emitter.onNext(data);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
