package com.syrous.yccestudentlibraryjava.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.R;

public class FragmentUpload extends Fragment {

    private static int REQUEST_CODE = 1001;

    String[] options= {"MSE", "ESE", "Resources"};

    Button pick_file;

    TextView path_display;

    Intent myFileIntent;

    public static FragmentUpload newIntent(){
        return new FragmentUpload();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_upload, container, false);

        // Spinner
        Spinner spinner=  v.findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Item Selected ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // File pick
        path_display = v.findViewById(R.id.path_tester);
        pick_file = v.findViewById(R.id.file_pick);

        pick_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent= new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("pdf");
                startActivityForResult(myFileIntent, REQUEST_CODE);

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){

        }
    }

}
