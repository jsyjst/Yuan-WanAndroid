package com.example.yuan_wanandroid.event;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   : 收藏
 * </pre>
 */


public class AutoRefreshEvent {
    private boolean isAutoRefresh ;
    public AutoRefreshEvent(boolean isAutoRefresh){
        this.isAutoRefresh = isAutoRefresh;
    }
    public boolean isAutoRefresh(){
        return isAutoRefresh;
    }
}
