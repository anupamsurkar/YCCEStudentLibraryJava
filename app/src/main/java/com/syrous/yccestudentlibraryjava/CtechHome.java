package com.syrous.yccestudentlibraryjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class CtechHome extends AppCompatActivity {

    RecyclerView recyclerView;
    CtechAdapter ctechAdapter;

    String[] semname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ctech_home);

        recyclerView= findViewById(R.id.ctech_home_recycler);

        semname= getResources().getStringArray(R.array.semesters);

        ctechAdapter = new CtechAdapter(CtechHome.this , semname);

        recyclerView.setAdapter(ctechAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
