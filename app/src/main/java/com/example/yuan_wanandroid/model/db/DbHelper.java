package com.example.yuan_wanandroid.model.db;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 数据库操作接口
 * </pre>
 */


public interface DbHelper {
    /**
     * 添加历史记录
     */
    boolean addHistory(String key);

    /**
     * 删除某一条历史记录
     */
    int deleteOneHistory(String key);

    /**
     * 删除所有历史记录
     */
    int deleteAllHistory();

    /**
     * 查找某一条历史记录是否存在
     */
    boolean isExistHistory(String key);

    /**
     * 获得所有历史记录
     */
    List<String> getAllHistory();
}
