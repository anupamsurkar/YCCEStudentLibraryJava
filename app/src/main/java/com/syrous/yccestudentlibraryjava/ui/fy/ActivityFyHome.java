package com.syrous.yccestudentlibraryjava.ui.fy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.GenAdapter;

/**
 * Created By : Syrous
 * date : 15/2/20
 */

public class ActivityFyHome extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String[] semName;
    private boolean isAdded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fy_activity_home);

        recyclerView= findViewById(R.id.fy_home_recycler);
        semName = getResources().getStringArray(R.array.semesters_fy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        if(isAdded){
            GenAdapter genAdapter = new GenAdapter(getApplicationContext(), semName,
                    R.layout.fy_sem_card, R.id.fy_sem_num);
            recyclerView.setAdapter(genAdapter);
            isAdded = false;
        }
    }
}
