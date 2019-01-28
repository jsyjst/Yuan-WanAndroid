package com.example.yuan_wanandroid.app;

import android.graphics.Color;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   : 常量类
 * </pre>
 */


public class Constant {
    //Url
    public final static String BASE_URL = "http://www.wanandroid.com/";

    //文章详细的url的key
    public final static String KEY_ARTICLE_URL="article_url";
    public final static String KEY_ARTICLE_TITLE="article_title";
    public final static String KEY_ARTICLE_ID ="article_id";
    public final static String KEY_ARTICLE_COLLECT ="article_collect";
    public final static String KEY_ARTICLE_COLLECT_HIDE="article_collect_hide";


    //二级体系key
    public final static String KEY_SYSTEM_FIRST_NAME="system_first_name";
    public final static String KEY_SYSTEM_SECOND_NAME_LIST="system_second_name_list";
    public final static String KEY_SYSTEM_SECOND_ID_LIST="system_second_id_list";

    //BaseLoadingState
    public static final int NORMAL_STATE = 0;
    public static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;

    //回调
    public static final int REQUEST_ARTICLE_ACTIVITY=0;
    public static final int REQUEST_COLLECTION_ACTIVITY=1;

    /**
     * 搜索标签颜色
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

}
