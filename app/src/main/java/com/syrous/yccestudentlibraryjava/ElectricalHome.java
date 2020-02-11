package com.syrous.yccestudentlibraryjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ElectricalHome extends AppCompatActivity {

    RecyclerView recyclerView;
    ElectricalAdapter electricalAdapter;

    String[] semname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_electrical_home);

        recyclerView= findViewById(R.id.electrical_home_recycler);

        semname= getResources().getStringArray(R.array.semesters);

        electricalAdapter = new ElectricalAdapter(ElectricalHome.this , semname);

        recyclerView.setAdapter(electricalAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
