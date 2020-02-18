package com.syrous.yccestudentlibraryjava.ui.login;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.ui.SingleFragmentActivity;

public class ActivityLogin extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return FragmentLogin.newInstance();
    }

    public static Intent newIntent(Context packageContext){
        return new Intent(packageContext, ActivityLogin.class);
    }
}
