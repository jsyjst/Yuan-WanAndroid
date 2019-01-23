package com.example.yuan_wanandroid.model.http;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BannerData;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.FirstSystem;

import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 网络操作接口
 * </pre>
 */


public interface NetworkHelper {

    /**
     *  home
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();//获取轮播图的数据
    Observable<BaseResponse<Articles>> getArticles(int pageNum);//获取首页文章

    /**
     * system
     */
    Observable<BaseResponse<List<FirstSystem>>> getFirstSystemData(); //获取知识体系的一级目录
    Observable<BaseResponse<Articles>> getSecondSystemArticles(int pageNum,int id);//获取二级体系文章


}
