package com.syrous.yccestudentlibraryjava.ui.paper_pager;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String path;
    private Activity activity;

    public ViewPagerAdapter(@NonNull FragmentManager fm, Lifecycle lifecycle, Activity activity, String path) {
        super(fm, lifecycle);
        this.activity = activity;
        this.path = path;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return MseFragment.newInstance(activity, path, "mse");
            case 1:
                return EseFragment.newInstance(activity, path, "ese");
            default:
                return StudyMaterialFragment.newInstance(activity, path, "resources");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
