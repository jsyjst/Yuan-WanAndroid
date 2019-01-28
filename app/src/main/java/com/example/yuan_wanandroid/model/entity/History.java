package com.example.yuan_wanandroid.model.entity;

import org.litepal.crud.LitePalSupport;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 搜素历史
 * </pre>
 */


public class History extends LitePalSupport{
    private int id;
    private String key;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
