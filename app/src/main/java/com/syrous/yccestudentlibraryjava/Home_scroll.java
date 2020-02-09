package com.syrous.yccestudentlibraryjava;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Home_scroll extends AppCompatActivity {



    List<Departments> oneDept;

    //OtherFeatures RecyclerView
    ArrayList<OtherFeatures> otherFeatures;
    OfAdapter ofAdapter;


    RecyclerView OfRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent= new Intent(Home_scroll.this, CivilHOme.class);
                startActivity(intent);
            }
        });

        oneDept= new ArrayList<>();
        oneDept.add(new Departments("First Year", R.drawable.baby));
        oneDept.add(new Departments("C.Tech", R.drawable.api));
        oneDept.add(new Departments("Civil", R.drawable.crane));
        oneDept.add(new Departments("Electrical", R.drawable.electricmotor));
        oneDept.add(new Departments("ETC", R.drawable.tower));
        oneDept.add(new Departments("Electronics", R.drawable.cpu));
        oneDept.add(new Departments("Mechanical", R.drawable.work));
        oneDept.add(new Departments("IT", R.drawable.tv));


        RecyclerView mrv= (RecyclerView) findViewById(R.id.home_recycler);

        DepartmentsAdapter myAdapter = new DepartmentsAdapter(this, oneDept);
        mrv.setLayoutManager(new GridLayoutManager(this, 4));
        mrv.setAdapter(myAdapter);



        //Other Features

        OfRecycler = findViewById(R.id.recycler_other_features);

        Integer[] feature_logos = {R.drawable.baby, R.drawable.api,
                R.drawable.tower, R.drawable.tv,
                R.drawable.crane, R.drawable.work, R.drawable.cpu};

        String[] feature_name = { "Upload Resources", "Attendance", "Online Fee Payment", "ESE Answer sheets", "Exam Dorm Acceptance", "Class Schedule", "Logout"};

        otherFeatures = new ArrayList<>();

        for( int i= 0; i<feature_logos.length; i++){
            OtherFeatures ofeatures = new OtherFeatures(feature_logos[i], feature_name[i]);
            otherFeatures.add(ofeatures);



        }
        LinearLayoutManager olayoutManager= new LinearLayoutManager(Home_scroll.this, LinearLayoutManager.HORIZONTAL, false);
        OfRecycler.setLayoutManager(olayoutManager);
        OfRecycler.setItemAnimator(new DefaultItemAnimator());

        ofAdapter = new OfAdapter(Home_scroll.this, otherFeatures);

        OfRecycler.setAdapter(ofAdapter);





    }
}
