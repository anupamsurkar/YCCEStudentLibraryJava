package com.syrous.yccestudentlibraryjava.Activities;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.Fragments.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}
