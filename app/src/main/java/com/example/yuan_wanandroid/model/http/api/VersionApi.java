package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.Version;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/04/16
 *     desc   : 版本api
 * </pre>
 */

public interface VersionApi {
    /**
     * 自己github上的下载地址
     * 格式为https://api.github.com/repos/userName/仓库名称/releases/latest
     */
    @GET("https://api.github.com/repos/jsyjst/Yuan-WanAndroid/releases/latest")
    Observable<Version> getVersionDetail();
}
