package com.example.yuan_wanandroid.view.person;

import android.graphics.Color;
import android.widget.LinearLayout;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.base.fragment.BaseFragment;
import com.example.yuan_wanandroid.utils.StatusBarUtil;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 个人模块
 * </pre>
 */


public class PersonFragment extends BaseFragment {
    @BindView(R.id.headPic)
    CircleImageView mHeadPic;
    @BindView(R.id.personLinear)
    LinearLayout mPersonLinear;

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_person;
    }

    @Override
    public void setStatusBarColor() {
        StatusBarUtil.immersiveInFragments(mActivity,Color.TRANSPARENT,1);
    }

}
