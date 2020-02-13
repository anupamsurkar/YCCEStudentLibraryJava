package com.syrous.yccestudentlibraryjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MechHome extends AppCompatActivity {

    RecyclerView recyclerView;
    MechAdapter mechAdapter;

    String[] semname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_home);

        recyclerView= findViewById(R.id.mech_home_recycler);

        semname= getResources().getStringArray(R.array.semesters);

        mechAdapter = new MechAdapter(MechHome.this , semname);

        recyclerView.setAdapter(mechAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
