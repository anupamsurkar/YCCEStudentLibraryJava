package com.syrous.yccestudentlibraryjava.Activities;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.Fragments.SplashFragment;

public class SplashActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return SplashFragment.newInstance();
    }


}
