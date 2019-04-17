package com.example.yuan_wanandroid.di.module;

import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.model.http.api.CollectApis;
import com.example.yuan_wanandroid.model.http.api.HomeApis;
import com.example.yuan_wanandroid.model.http.api.PersonApis;
import com.example.yuan_wanandroid.model.http.api.ProjectApis;
import com.example.yuan_wanandroid.model.http.api.SearchApis;
import com.example.yuan_wanandroid.model.http.api.SystemApis;
import com.example.yuan_wanandroid.model.http.api.VersionApi;
import com.example.yuan_wanandroid.model.http.api.WxApis;
import com.example.yuan_wanandroid.model.http.interceptor.CacheInterceptor;
import com.example.yuan_wanandroid.model.http.interceptor.ReadCookiesInterceptor;
import com.example.yuan_wanandroid.model.http.interceptor.SaveCookiesInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   : App的module
 * </pre>
 */


@Module
public class AppModule {
    private final App mApp;

    public AppModule(App app){
        mApp = app;
    }

    @Provides
    @Singleton
    App provideApp(){
        return mApp;
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder(){
        return new OkHttpClient.Builder();
    }
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder){

        //设置缓存
        File cacheDir = new File(Constant.PATH_NET_CACHE);
        Cache cache = new Cache(cacheDir,1024 * 1024 * 10);//缓存最大大小10m
        builder.cache(cache);
        /**
         * 这里不使用addNetInterceptor的原因是，网络拦截器比应用拦截器慢执行，
         * 没有网的情况下，直接就ConnectException了，根本不会走到interceptor里面去了。
         */
        builder.addInterceptor(new CacheInterceptor());

        //错误重连
        builder.retryOnConnectionFailure(true);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //cookie
        builder.addInterceptor(new SaveCookiesInterceptor(mApp));
        builder.addInterceptor(new ReadCookiesInterceptor(mApp));
        //builder.cookieJar(new CookiesManager(mApp));
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    HomeApis provideHomeApis(Retrofit retrofit){
        return retrofit.create(HomeApis.class);
    }

    @Provides
    @Singleton
    SystemApis provideSystemApis(Retrofit retrofit){
        return retrofit.create(SystemApis.class);
    }

    @Provides
    @Singleton
    WxApis provideWxApis(Retrofit retrofit){
        return retrofit.create(WxApis.class);
    }

    @Provides
    @Singleton
    ProjectApis provideProjectApis(Retrofit retrofit){
        return retrofit.create(ProjectApis.class);
    }

    @Provides
    @Singleton
    PersonApis providePersonApis(Retrofit retrofit){
        return retrofit.create(PersonApis.class);
    }

    @Provides
    @Singleton
    CollectApis provideCollectApis(Retrofit retrofit){
        return retrofit.create(CollectApis.class);
    }

    @Provides
    @Singleton
    SearchApis provideSearchApis(Retrofit retrofit){
        return retrofit.create(SearchApis.class);
    }

    @Provides
    @Singleton
    VersionApi provideVersionApi(Retrofit retrofit){
        return retrofit.create(VersionApi.class);
    }
}
