package com.example.yuan_wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.model.entity.Article;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/22
 *     desc   : 首页文章的适配器
 * </pre>
 */


public class ArticlesAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {
    public ArticlesAdapter(int layoutResId, List<Article> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        if(item == null) return;
        helper.setText(R.id.homeItemAuthorTv,item.getAuthor())
                .setText(R.id.homeItemDateTv,item.getNiceDate())
                .setText(R.id.homeItemTitleTv,item.getTitle())
                .setText(R.id.homeItemTypeTv,item.getSuperChapterName()+" / "+item.getChapterName());

    }
}
