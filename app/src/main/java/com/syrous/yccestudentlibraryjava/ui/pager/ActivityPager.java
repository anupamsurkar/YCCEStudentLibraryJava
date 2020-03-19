package com.syrous.yccestudentlibraryjava.ui.pager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;

public class ActivityPager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        String subject = getIntent().getStringExtra(GlobalConstants.SUBJECT_NAME);
        String dept = getIntent().getStringExtra(GlobalConstants.DEPARTMENT_NAME);
        String courseCode = getIntent().getStringExtra(GlobalConstants.COURSE_CODE);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        TabLayout tabLayout1 = findViewById(R.id.myTabLayout);
        String toolbar_text = dept;

        assert toolbar_text != null;
        if(toolbar_text.equals(getString(R.string.ctech))){
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_ct));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_ct));

        }else if(toolbar_text.equals(getString(R.string.civil))){
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolabr_cv));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolabr_cv));

        }
        else if(toolbar_text.equals(getString(R.string.electrical))){
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_el));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_el));

        }
        else if(toolbar_text.equals(getString(R.string.electronics))){
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_ee));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_ee));

        }
        else if(toolbar_text.equals(getString(R.string.etc))){
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_etc));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_etc));

        }
        else if(toolbar_text.equals(getString(R.string.fy))){
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_fy));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_fy));

        }
        else if(toolbar_text.equals(getString(R.string.it))){
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_it));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_it));

        }
        else if(toolbar_text.equals(getString(R.string.mechanical))){
            toolbar.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_me));
            tabLayout1.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_me));

        }

        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle(subject);
        setSupportActionBar(toolbar);

        String path = getIntent().getStringExtra(GlobalConstants.DOWNLOAD_SERVER_PATH);
        path = path + "/" + courseCode.toLowerCase();
        //Viewpager2
        ViewPager2 viewPager2 = findViewById(R.id.myViewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), this, path));

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
