package com.example.yuan_wanandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.FirstSystem;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   : 一级知识体系的适配器
 * </pre>
 */


public class FirstSystemAdapter extends BaseQuickAdapter<FirstSystem,BaseViewHolder>{

    public FirstSystemAdapter(int layoutResId, @Nullable List<FirstSystem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstSystem item) {
        if(item == null) return;
        String secondSystemText = "";
        for(FirstSystem.ChildrenBean secondSystem: item.getChildren() ){
            secondSystemText += secondSystem.getName()+"   ";
        }
        helper.setText(R.id.systemItemFirstTv,item.getName())
                .setText(R.id.systemItemSecondTv,secondSystemText);
    }
}
