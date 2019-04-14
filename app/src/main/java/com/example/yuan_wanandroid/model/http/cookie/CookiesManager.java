package com.example.yuan_wanandroid.model.http.cookie;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.yuan_wanandroid.app.App;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/04/14
 *     desc   : 管理cookieManger
 * </pre>
 */

public class CookiesManager implements CookieJar {
    private static Context mContext;
    private static PersistentCookieStore cookieStore;

    public CookiesManager(Context context) {
        mContext = context;
        if (cookieStore == null ) {
            cookieStore = new PersistentCookieStore(mContext);
        }

    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

}
