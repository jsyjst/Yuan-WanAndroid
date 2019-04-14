package com.example.yuan_wanandroid.model.http.interceptor;

import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.utils.CommonUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/04/14
 *     desc   : 缓存拦截器
 * </pre>
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        boolean isNetConnection = CommonUtils.isNetworkConnected(App.getContext());
        //如果没有网络从缓存中读取
        if(!isNetConnection){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if(isNetConnection){
            int maxAge = 0;
            response = response.newBuilder()
                    .header("Cache-Control", "public, max-age=" +maxAge)// 有网络时,不缓存
                    .removeHeader("Pragma")
                    .build();
        }else{
            // 无网络时，设置超时为1周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}
