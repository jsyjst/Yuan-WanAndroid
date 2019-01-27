package com.example.yuan_wanandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.utils.ImageUtil;

import java.util.List;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目item的适配器
 * </pre>
 */


public class ProjectAdapter extends BaseQuickAdapter<Article,BaseViewHolder>{

    public ProjectAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.projectTitleTv,item.getTitle())
                .setText(R.id.projectDecsTv,item.getDesc())
                .setText(R.id.projectAuthorTv,item.getAuthor())
                .setText(R.id.projectDateTv,item.getNiceDate())
                .addOnClickListener(R.id.projectLoveIv);

        ImageUtil.loadImage(mContext,helper.getView(R.id.projectPicIv),item.getEnvelopePic());
        if(item.isCollect()){
            helper.getView(R.id.projectLoveIv).setSelected(true);
        }else{
            helper.getView(R.id.projectLoveIv).setSelected(false);
        }
    }

}
