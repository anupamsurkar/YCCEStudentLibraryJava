package com.syrous.yccestudentlibraryjava;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Home_scroll extends AppCompatActivity {

    List<Departments> oneDept;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    }
}
