package com.syrous.yccestudentlibraryjava.ui.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelUser;
import com.syrous.yccestudentlibraryjava.data.User;

public class ActivityHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ModelUser user;
    private static final String USER = "user";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.AboutUs, R.id.ContactUs, R.id.ProfilePage)
                .setOpenableLayout(drawerLayout)
                .build();
        navigationView.setNavigationItemSelectedListener(this);
        navController = Navigation.findNavController(this, R.id.nav_host);

        View headerView = navigationView.getHeaderView(0);

        TextView studentName = headerView.findViewById(R.id.studentName);
        TextView studentEmail = headerView.findViewById(R.id.studentEmail);
         user = User.get(getApplicationContext()).getUser();

        assert user != null;
        studentName.setText(user.getUserName());
        studentEmail.setText(user.getEmailId());

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
       user = User.get(getApplicationContext()).getUser();
        outState.putSerializable(USER, user);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        user = (ModelUser) savedInstanceState.getSerializable(USER);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.AboutUs:
                navController.navigate(R.id.AboutUs);
                drawerLayout.closeDrawers();
                break;
            case R.id.ContactUs:
                navController.navigate(R.id.ContactUs);
                drawerLayout.closeDrawers();
                break;
            case R.id.ProfilePage:
                navController.navigate(R.id.ProfilePage);
                drawerLayout.closeDrawers();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        navController.navigate(R.id.HomePage);
    }
}
