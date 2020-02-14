package com.syrous.yccestudentlibraryjava.ui.ee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.syrous.yccestudentlibraryjava.R;

public class ElectronicsHome extends AppCompatActivity {

    RecyclerView recyclerView;
    ElectronicsAdapter electronicsAdapter;

    String[] semname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ee_activity_home);

        recyclerView= findViewById(R.id.electronics_home_recycler);

        semname= getResources().getStringArray(R.array.semesters);

        electronicsAdapter = new ElectronicsAdapter(ElectronicsHome.this , semname);

        recyclerView.setAdapter(electronicsAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
