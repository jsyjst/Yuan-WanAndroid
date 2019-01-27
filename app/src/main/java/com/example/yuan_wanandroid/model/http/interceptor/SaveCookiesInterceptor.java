package com.example.yuan_wanandroid.model.http.interceptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 保存cookie
 * </pre>
 */


public class SaveCookiesInterceptor implements Interceptor{
    private Context mContext;
    private String TAG="cookies";
    public SaveCookiesInterceptor(Context context){
        mContext=context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SharedPreferences.Editor editor= PreferenceManager.getDefaultSharedPreferences(mContext).edit();
            editor .putStringSet(TAG, cookies).apply();

        }
        return originalResponse;
    }
}
