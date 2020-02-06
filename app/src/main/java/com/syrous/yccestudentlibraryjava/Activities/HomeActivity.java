package com.syrous.yccestudentlibraryjava.Activities;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.Fragments.HomeFragment;

public class HomeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return HomeFragment.newInstance();
    }
    
}
