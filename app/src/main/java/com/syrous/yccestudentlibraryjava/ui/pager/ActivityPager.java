package com.syrous.yccestudentlibraryjava.ui.pager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.syrous.yccestudentlibraryjava.R;

public class ActivityPager extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        toolbar =  findViewById(R.id.myToolbar);
        toolbar.setTitle(" ");
        tabLayout =  findViewById(R.id.tablayout);
        viewPager =  findViewById(R.id.myViewPager);

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MseFragment(),"MSE");
        viewPagerAdapter.addFragment(new EseFragment(),"ESE");
        viewPagerAdapter.addFragment(new StudyMaterialFragment(),"Study Material");
        viewPager.setAdapter(viewPagerAdapter);

    }

}
