package com.example.yuan_wanandroid.app;

import android.graphics.Color;
import android.os.Environment;

import com.example.yuan_wanandroid.utils.FileUtil;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   : 常量类
 * </pre>
 */


public class Constant {
    //Url
    public final static String BASE_URL = "https://www.wanandroid.com/";

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

    //prefs
    public static final String SHARED_PREFERENCES_NAME = "prefs";
    public static final String KEY_PREFERS_NIGHT="night";
    public static final String KEY_PREFERS_NO_IMG="no_img";
    public static final String KEY_NIGHT_CHANGE="night_change";
    public static final String KEY_PREFERS_AUTO_CACHE="auto_cache";
    public static final String KEY_PREFERS_DOWNLOAD_ID="download_id";
    public static final String KEY_PREFERS_NAV_ITEM="nav_item"; //底部导航item，用于打开夜间模式后重新打开app

    //setting
    public static final String EMAIL_ADDRESS = "1443481947@qq.com";

    //path
    public static final String PATH_NET_CACHE = FileUtil.getCachePath(App.getContext(), "netData");
    public static final String PATH_APK_DOWNLOAD_MANAGER = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/WanAndroid.apk";
    public static final String PATH_APK_BROWSE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/app-release.apk";

    //download
    public static final String KEY_APK_URL="apk_url";

    //bugly
    public static final String BUGLY_APP_ID="eb2d4b003b";
}
