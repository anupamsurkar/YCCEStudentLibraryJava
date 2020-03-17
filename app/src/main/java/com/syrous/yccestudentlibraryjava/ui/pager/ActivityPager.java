package com.syrous.yccestudentlibraryjava.ui.pager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.subject.ActivitySubject;

public class ActivityPager extends AppCompatActivity {

    private Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);


        String subject = getIntent().getStringExtra("subanme");

        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle(subject);

        //Back button

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivitySubject.class));
                finish();
            }
        });

        //Viewpager2

        ViewPager2 viewPager2 = findViewById(R.id.myViewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.myTabLayout);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Mse");
                        break;
                    case 1:
                        tab.setText("Ese");
                        break;
                    case 2:
                        tab.setText("study material");
                        break;
                }
            }
        }
        );
        tabLayoutMediator.attach();

    }

}
