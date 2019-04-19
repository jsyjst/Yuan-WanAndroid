package com.example.yuan_wanandroid.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.utils.TypefacesUtil;
import com.example.yuan_wanandroid.widget.Lead;
import com.example.yuan_wanandroid.widget.LeadTextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderActivity extends AppCompatActivity {

    @BindView(R.id.leadTv)
    LeadTextView leadTv;
    private Lead mLead;
    private static final int mDuration = 3000;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        ButterKnife.bind(this);
        initLeadText();
    }

    private void initLeadText(){
        leadTv.setTypeface(TypefacesUtil.get(this,"Satisfy-Regular.ttf"));
        mLead = new Lead(mDuration);
        mLead.start(leadTv);
        mHandler.postDelayed(() -> {
            startActivity(new Intent(LeaderActivity.this,MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        },mDuration);
    }

    @Override
    protected void onDestroy() {
        if(mLead!=null){
            mLead.cancel();
        }
        //移除MessageQueue中的消息，避免出现内存泄漏。如果没有移除，将会出现内存泄漏
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        super.onDestroy();
    }

}
