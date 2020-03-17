package com.syrous.yccestudentlibraryjava.ui.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String path;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String path) {
        super(fragmentActivity);
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
                return MseFragment.newInstance(path, "mse");
            case 1:
                return EseFragment.newInstance(path, "ese");
            default:
                return StudyMaterialFragment.newInstance(path, "resources");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
