package com.syrous.yccestudentlibraryjava.ui.splash;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.ui.SingleFragmentActivity;

public class ActivitySplash extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return FragmentSplash.newInstance();
    }


}
