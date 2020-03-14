package com.syrous.yccestudentlibraryjava.ui.it;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.GenAdapter;

public class ActivityItHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String dept;
    private boolean isAdded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it_activity_home);

        dept = getIntent().getStringExtra(GlobalConstants.DEPARTMENT_NAME);

        recyclerView= findViewById(R.id.it_home_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        if(isAdded){
            GenAdapter genAdapter = new GenAdapter(getApplicationContext(), dept,
                    R.layout.it_sem_card, R.id.it_sem_num);
            recyclerView.setAdapter(genAdapter);
            isAdded = false;
        }
    }
}
