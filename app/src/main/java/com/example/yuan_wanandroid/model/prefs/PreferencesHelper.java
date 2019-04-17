package com.example.yuan_wanandroid.model.prefs;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/30
 *     desc   : SharedPreferences操作接口
 * </pre>
 */


public interface PreferencesHelper {
    void setNightStyleState(boolean isNight); //保存夜间模式设置
    boolean getNightStyleState(); //得到夜间模式的设置
    void setNoImgState(boolean isNoImg);//保存无图模式设置
    boolean getNoImgStyleState();//得到无图模式的设置
    void setAutoCacheState(boolean isAutoCache);//保存自动缓存设置，自动缓存为文章详细的内容
    boolean getAutoCacheState(); //得到自动缓存设置
    void setDownloadId(long id);//保存更新apk的记录
    long getDownloadId();//得到下载记录
}
