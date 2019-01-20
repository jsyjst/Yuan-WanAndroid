package com.example.yuan_wanandroid.component;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/20
 *     desc   : 活动收集，一键退出app
 * </pre>
 */


public class ActivityCollector {
    private static ActivityCollector mActivityCollector = null;
    private ActivityCollector(){}

    //DCL实现单例
    public static ActivityCollector getInstance(){
        if(mActivityCollector == null){
            synchronized (ActivityCollector.class){
                if(mActivityCollector == null){
                    mActivityCollector = new ActivityCollector();
                }
            }
        }
        return mActivityCollector;
    }

    private Set<Activity> allActivities;

    public void addActiviy(Activity activity){
        if(allActivities == null){
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(Activity activity){
        if(allActivities != null){
            allActivities.remove(activity);
        }
    }

    public void exitApp(){
        if(allActivities != null){
            synchronized (allActivities){
                for(Activity activity:allActivities){
                    activity.finish();
                }
            }
        }
    }
}
