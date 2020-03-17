package com.syrous.yccestudentlibraryjava.ui.pager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {



    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
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
                return new MseFragment();
            case 1:
                return new EseFragment();
            default:
                return new StudyMaterialFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
