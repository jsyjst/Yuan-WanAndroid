package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 收藏功能接口
 * </pre>
 */


public interface CollectApis {
    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse> collectArticles(@Path("id") int id);
    /**
     * 取消收藏
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse> unCollectArticles(@Path("id") int id);

}
