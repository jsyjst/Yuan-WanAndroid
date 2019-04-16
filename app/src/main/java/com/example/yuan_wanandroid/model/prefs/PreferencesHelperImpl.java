package com.example.yuan_wanandroid.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;

import org.litepal.util.Const;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/30
 *     desc   : SharedPreferences操作实现累
 * </pre>
 */


public class PreferencesHelperImpl implements PreferencesHelper{
    private SharedPreferences mPreferences;

    @Inject
    public PreferencesHelperImpl(){
        mPreferences = App.getContext().getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void setNightStyleState(boolean isNight) {
        mPreferences.edit().putBoolean(Constant.KEY_PREFERS_NIGHT,isNight).apply();
    }

    @Override
    public boolean getNightStyleState() {
        return mPreferences.getBoolean(Constant.KEY_PREFERS_NIGHT,false);
    }

    @Override
    public void setNoImgState(boolean isNoImg) {
        mPreferences.edit().putBoolean(Constant.KEY_PREFERS_NO_IMG,isNoImg).apply();
    }

    @Override
    public boolean getNoImgStyleState() {
        return mPreferences.getBoolean(Constant.KEY_PREFERS_NO_IMG,false);
    }

    @Override
    public void setAutoCacheState(boolean isAutoCache) {
        mPreferences.edit().putBoolean(Constant.KEY_PREFERS_AUTO_CACHE,isAutoCache).apply();
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferences.getBoolean(Constant.KEY_PREFERS_AUTO_CACHE,true);
    }
}
