package com.syrous.yccestudentlibraryjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ItHome extends AppCompatActivity {

    RecyclerView recyclerView;
    ItAdapter itAdapter;

    String[] semname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_it_home);
        recyclerView= findViewById(R.id.it_home_recycler);

        semname= getResources().getStringArray(R.array.semesters);

        itAdapter = new ItAdapter(ItHome.this , semname);

        recyclerView.setAdapter(itAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
