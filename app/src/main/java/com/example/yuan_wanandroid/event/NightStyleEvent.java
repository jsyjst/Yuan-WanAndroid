package com.example.yuan_wanandroid.event;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   : 夜间模式
 * </pre>
 */

public class NightStyleEvent {
    private boolean isNight;

    public NightStyleEvent(boolean isNight) {
        this.isNight = isNight;
    }

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }
}
