package com.example.yuan_wanandroid.model.http.interceptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 读取cookie
 * </pre>
 */


public class ReadCookiesInterceptor implements  Interceptor{
    private Context mContext;
    private String TAG="cookies";
    public ReadCookiesInterceptor(Context context){
        mContext=context;
    }
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(mContext);
        HashSet<String> preferences = (HashSet)pref.getStringSet(TAG, new HashSet<>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}