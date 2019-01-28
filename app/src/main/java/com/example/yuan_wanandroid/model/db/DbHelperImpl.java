package com.example.yuan_wanandroid.model.db;

import com.example.yuan_wanandroid.model.entity.History;
import com.example.yuan_wanandroid.utils.CommonUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 数据库操作的实现类
 * </pre>
 */


public class DbHelperImpl implements DbHelper {

    @Inject
    public DbHelperImpl(){}

    @Override
    public boolean addHistory(String key) {
        History history = new History();
        history.setKey(key);
        return history.save();
    }

    @Override
    public int deleteOneHistory(String key) {
        return LitePal.deleteAll(History.class,"key=?",key);
    }

    @Override
    public int deleteAllHistory() {
        return LitePal.deleteAll(History.class);
    }

    @Override
    public boolean isExistHistory(String key) {
        return !CommonUtils.isEmptyList(LitePal.where("key=?",key).find(History.class));
    }

    @Override
    public List<String> getAllHistory() {
        List<History> historyList = LitePal.findAll(History.class);
        if(CommonUtils.isEmptyList(historyList)) return null;
        List<String> histories = new ArrayList<>();
        for(int i=historyList.size()-1;i>=0;i--){
            histories.add(historyList.get(i).getKey());
        }
        return histories;
    }
}
