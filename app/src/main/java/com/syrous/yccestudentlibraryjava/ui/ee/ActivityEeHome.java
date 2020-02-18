package com.syrous.yccestudentlibraryjava.ui.ee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.GenAdapter;

public class ActivityEeHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private boolean isAdded = true;
    private String[] semName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ee_activity_home);

        recyclerView= findViewById(R.id.electronics_home_recycler);

        semName = getResources().getStringArray(R.array.semesters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        if(isAdded){
            GenAdapter genAdapter = new GenAdapter(getApplicationContext(), semName,
                    R.layout.ee_sem_card, R.id.electronics_sem_num);
            recyclerView.setAdapter(genAdapter);
            isAdded = false;
        }
    }
}
