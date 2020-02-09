package com.syrous.yccestudentlibraryjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class CivilHOme extends AppCompatActivity {

    RecyclerView recyclerView;
    CivilAdapter civilAdapter;

    String[] semname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_home);

        recyclerView= findViewById(R.id.civil_home_recycler);

        semname= getResources().getStringArray(R.array.semesters);

      civilAdapter = new CivilAdapter(CivilHOme.this , semname);

      recyclerView.setAdapter(civilAdapter);

      recyclerView.setLayoutManager(new LinearLayoutManager(this));






    }
}
