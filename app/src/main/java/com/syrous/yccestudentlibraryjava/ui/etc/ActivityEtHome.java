package com.syrous.yccestudentlibraryjava.ui.etc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.GenAdapter;

public class ActivityEtHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String dept;
    private boolean isAdded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.et_activity_home);

        dept = getIntent().getStringExtra(GlobalConstants.DEPARTMENT_NAME);

        recyclerView= findViewById(R.id.etc_home_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        if(isAdded){
            GenAdapter genAdapter = new GenAdapter(getApplicationContext(), dept,
                    R.layout.et_sem_card, R.id.etc_sem_num);
            recyclerView.setAdapter(genAdapter);
            isAdded = false;
        }
    }
}
