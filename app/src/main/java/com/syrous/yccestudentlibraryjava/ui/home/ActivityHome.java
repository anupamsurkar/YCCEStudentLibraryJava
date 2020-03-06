package com.syrous.yccestudentlibraryjava.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.syrous.yccestudentlibraryjava.R;

public class ActivityHome extends AppCompatActivity {

    private AppBarConfiguration mAppBarConf;
    private ActionBarDrawerToggle mDrawerToggle;
    private static String TAG = "DrawerLayout";

    private DrawerLayout drawer;
    public Fragment createFragment() {
        return FragmentHome.newInstance();
    }

////    @Override
////    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////        switch (item.getItemId()){
////        case R.id.nav_profile:
////            Log.d(TAG, "Selected Item : Nav_Profile");
////            break;
////            case R.id.nav_about_us:
////                Log.d(TAG, "Selected Item : Nav_About_Us");
////                break;
////            case R.id.nav_contact_us:
////                Log.d(TAG, "Selected Item : Nav_Contact_Us");
////                break;
////
////        }
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.ycce_student_library);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);

        drawer.addDrawerListener(mDrawerToggle);
       mDrawerToggle.setDrawerIndicatorEnabled(true);

        mDrawerToggle.syncState();

        mAppBarConf = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_about_us, R.id.nav_contact_us,
                R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConf);
        NavigationUI.setupWithNavController(navigationView, navController);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.nav_host_fragment);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.nav_host_fragment, fragment)
                    .commit();
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConf)
                || super.onSupportNavigateUp();
    }


}
