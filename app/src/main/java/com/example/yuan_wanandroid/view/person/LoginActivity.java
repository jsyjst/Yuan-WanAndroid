package com.example.yuan_wanandroid.view.person;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.utils.StatusBarUtil;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.immersiveInImage(this);
        replaceFragment(LoginFragment.newInstance());
    }

    public void toRegisterFragment(){
        replaceFragment(RegisterFragment.newInstance());
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameContain,fragment);
        if(fragment instanceof RegisterFragment) transaction.addToBackStack(null);
        transaction.commit();
    }
}
