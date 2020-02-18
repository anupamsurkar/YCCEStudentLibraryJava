package com.syrous.yccestudentlibraryjava.ui.el;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.GenAdapter;

public class ElectricalHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] semName;
    private boolean isAdded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.el_activity_home);

        recyclerView= findViewById(R.id.electrical_home_recycler);
        semName = getResources().getStringArray(R.array.semesters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        if(isAdded){
            GenAdapter genAdapter = new GenAdapter(getApplicationContext(), semName,
                    R.layout.el_sem_card, R.id.electrical_sem_num);
            recyclerView.setAdapter(genAdapter);
            isAdded = false;
        }
    }
}
