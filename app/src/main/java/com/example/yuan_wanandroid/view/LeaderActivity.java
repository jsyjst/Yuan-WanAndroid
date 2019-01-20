package com.example.yuan_wanandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.utils.TypefacesUtil;
import com.example.yuan_wanandroid.widget.Lead;
import com.example.yuan_wanandroid.widget.LeadTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderActivity extends AppCompatActivity {

    @BindView(R.id.leadTv)
    LeadTextView leadTv;
    private static final int mDuration = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        ButterKnife.bind(this);
        initLeadText();
    }

    private void initLeadText(){
        leadTv.setTypeface(TypefacesUtil.get(this,"Satisfy-Regular.ttf"));
        final Lead lead = new Lead(mDuration);
        lead.start(leadTv);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LeaderActivity.this,MainActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        },mDuration);
    }
}
