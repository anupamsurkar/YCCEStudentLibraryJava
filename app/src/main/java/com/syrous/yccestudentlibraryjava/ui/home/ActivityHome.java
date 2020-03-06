package com.syrous.yccestudentlibraryjava.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.about_us.FragmentAboutUs;
import com.syrous.yccestudentlibraryjava.ui.contact_us.FragmentContactUs;
import com.syrous.yccestudentlibraryjava.ui.profile.FragmentProfile;

public class ActivityHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConf;
    private ActionBarDrawerToggle mDrawerToggle;
    private static String TAG = "DrawerLayout";

    private DrawerLayout drawer;
    public Fragment createFragment() {
        return FragmentHome.newInstance();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
        case R.id.nav_profile:
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new FragmentProfile()).commit();
            Log.d(TAG, "Selected Item : Nav_Profile");
            break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container, new FragmentAboutUs()).commit();
                Log.d(TAG, "Selected Item : Nav_About_Us");
                break;
            case R.id.nav_contact_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container, new FragmentContactUs()).commit();
                Log.d(TAG, "Selected Item : Nav_Contact_Us");
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.ycce_student_library);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
