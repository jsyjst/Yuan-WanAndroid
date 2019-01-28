package com.example.yuan_wanandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuan_wanandroid.R;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 历史记录的适配器
 * </pre>
 */


public class HistoryAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public HistoryAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.historyTv,item).addOnClickListener(R.id.deleteIv);
    }
}
