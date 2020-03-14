package com.syrous.yccestudentlibraryjava.ui.ee;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.GenAdapter;

public class ActivityEeHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private boolean isAdded = true;
    private String dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ee_activity_home);

        dept = getIntent().getStringExtra(GlobalConstants.DEPARTMENT_NAME);
        recyclerView= findViewById(R.id.electronics_home_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        if(isAdded){
            GenAdapter genAdapter = new GenAdapter(getApplicationContext(), dept,
                    R.layout.ee_sem_card, R.id.electronics_sem_num);
            recyclerView.setAdapter(genAdapter);
            isAdded = false;
        }
    }
}
