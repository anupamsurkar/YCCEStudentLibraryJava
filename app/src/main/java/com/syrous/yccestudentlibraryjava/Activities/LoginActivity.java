package com.syrous.yccestudentlibraryjava.Activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.Fragments.LoginFragment;
import com.syrous.yccestudentlibraryjava.R;

public class LoginActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();


    }
}
