package com.example.yuan_wanandroid.view.person;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.backBtn)
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.immersiveInImage(this);
        replaceFragment(LoginFragment.newInstance());
        backBtn.setOnClickListener(v -> finish());
    }

    /**
     * 移除注册界面
     */
    public void toRegisterFragment() {
        replaceFragment(RegisterFragment.newInstance());
    }

    /**
     * 切换登录和注册
     *
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContain, fragment);
        if (fragment instanceof RegisterFragment) transaction.addToBackStack(null);
        transaction.commit();
    }
}
