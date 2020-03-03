package com.syrous.yccestudentlibraryjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UploadActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String[] options= {"MSE", "ESE", "Resources"};

    Button pick_file;

    TextView path_display;

    Intent myFileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

       // Spinner
        Spinner spinner=  (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        // File pick

        path_display= (TextView)findViewById(R.id.path_tester);
        pick_file= (Button)findViewById(R.id.file_pick);

        pick_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent= new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("pdf");
                startActivityForResult(myFileIntent, 10);

            }
        });








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                    String path = data.getData().getPath();
                    path_display.setText(path);
                }

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(),"Selected"+ options[position], Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        // TODO- .......

    }
}
