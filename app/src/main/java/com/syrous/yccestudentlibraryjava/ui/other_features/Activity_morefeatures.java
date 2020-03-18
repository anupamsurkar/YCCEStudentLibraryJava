package com.syrous.yccestudentlibraryjava.ui.other_features;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.syrous.yccestudentlibraryjava.R;

import java.util.ArrayList;

public class Activity_morefeatures extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morefeatures);

        listView = findViewById(R.id.listview);

        ArrayList<String> mf_links = new ArrayList<>();

        mf_links.add("ad");
        mf_links.add("ad");
        mf_links.add("ad");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mf_links);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) listView.getItemAtPosition(position);
                Toast.makeText(Activity_morefeatures.this,clickedItem,Toast.LENGTH_LONG).show();
            }
        });
    }
}
