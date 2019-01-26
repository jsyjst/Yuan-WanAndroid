package com.example.yuan_wanandroid.component;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 订阅事件
 * </pre>
 */


public class RxBus {
    private final Subject<Object> mSubject;

    private RxBus() {
        mSubject = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * 发送一个事件
     */
    public void post(Object o){
        mSubject.onNext(o);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> toObservable(Class<T> eventType){
        return mSubject.ofType(eventType);
    }

    private static class Holder{
        static final RxBus INSTANCE = new RxBus();
    }
}
