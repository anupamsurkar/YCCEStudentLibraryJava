package com.syrous.yccestudentlibraryjava.ui.ctech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.GenAdapter;

public class ActivityCtHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] semName;
    private boolean isAdded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ct_activity_home);

        recyclerView= findViewById(R.id.ctech_home_recycler);
        semName = getResources().getStringArray(R.array.semesters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        if(isAdded){
            GenAdapter genAdapter = new GenAdapter(getApplicationContext(), semName,
                    R.layout.ct_sem_card, R.id.ctech_sem_num);
            recyclerView.setAdapter(genAdapter);
            isAdded = false;
        }
    }
}
