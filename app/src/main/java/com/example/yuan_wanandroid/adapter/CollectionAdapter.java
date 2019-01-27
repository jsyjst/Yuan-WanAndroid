package com.example.yuan_wanandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.model.entity.Collection;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/27
 *     desc   : 收藏列表的适配器
 * </pre>
 */


public class CollectionAdapter extends BaseQuickAdapter<Collection,BaseViewHolder>{

    public CollectionAdapter(int layoutResId, @Nullable List<Collection> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Collection item) {
        if(item == null) return;
        helper.setText(R.id.homeItemAuthorTv,item.getAuthor())
                .setText(R.id.homeItemDateTv,item.getNiceDate())
                .setText(R.id.homeItemTitleTv,item.getTitle())
                .setText(R.id.homeItemTypeTv,item.getChapterName())
                .setImageResource(R.id.homeItemLoveIv,R.drawable.ic_love)
                .addOnClickListener(R.id.homeItemLoveIv);
    }
}
