package com.example.yuan_wanandroid.model.http;

import com.example.yuan_wanandroid.model.entiy.BannerData;
import com.example.yuan_wanandroid.model.entiy.BaseResponse;

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
    Observable<BaseResponse<List<BannerData>>> getBannerData(); //获取轮播图的数据
}
