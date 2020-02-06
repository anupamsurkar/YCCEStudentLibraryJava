package com.syrous.yccestudentlibraryjava.Activities;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.Fragments.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }

    public static Intent newIntent(Context packageContext){
        return new Intent(packageContext, LoginActivity.class);
    }
}
