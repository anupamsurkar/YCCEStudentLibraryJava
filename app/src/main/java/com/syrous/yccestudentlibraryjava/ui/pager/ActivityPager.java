package com.syrous.yccestudentlibraryjava.ui.pager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;

public class ActivityPager extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        String subject = getIntent().getStringExtra(GlobalConstants.SUBJECT_NAME);

        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle(subject);
        setSupportActionBar(toolbar);

        String path = getIntent().getStringExtra(GlobalConstants.DOWNLOAD_SERVER_PATH);
        path = path + "/" + subject.toLowerCase();
        //Viewpager2
        ViewPager2 viewPager2 = findViewById(R.id.myViewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(this, path));

        TabLayout tabLayout = findViewById(R.id.myTabLayout);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, (tab, position) ->  {
                switch (position) {
                    case 0:
                        tab.setText("Mse");
                        break;
                    case 1:
                        tab.setText("Ese");
                        break;
                    case 2:
                        tab.setText("Study Material");
                        break;
                }
            });
        tabLayoutMediator.attach();

    }

}
