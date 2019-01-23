package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.FirstSystem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   : 知识体系的api集合
 * </pre>
 */


public interface SystemApis {

    /**
     * 一级知识体系的目录
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    Observable<BaseResponse<List<FirstSystem>>> getFirstSystemData();

    /**
     * 二级知识体系文章列表
     * http://www.wanandroid.com/article/list/0/json?cid=60
     */
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<Articles>> getSecondSystemArticles(@Path("pageNum") int pageNum, @Query("cid")int id);
}
