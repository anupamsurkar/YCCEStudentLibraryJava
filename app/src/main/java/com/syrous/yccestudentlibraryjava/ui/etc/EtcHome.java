package com.syrous.yccestudentlibraryjava.ui.etc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.syrous.yccestudentlibraryjava.R;

public class EtcHome extends AppCompatActivity {

    RecyclerView recyclerView;
    EtcAdapter etcAdapter;

    String[] semname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.et_activity_home);

        recyclerView= findViewById(R.id.etc_home_recycler);

        semname= getResources().getStringArray(R.array.semesters);

        etcAdapter = new EtcAdapter(EtcHome.this , semname);

        recyclerView.setAdapter(etcAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
