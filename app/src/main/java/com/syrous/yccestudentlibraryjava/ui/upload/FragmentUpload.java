package com.syrous.yccestudentlibraryjava.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.R;

import java.util.Random;

public class FragmentUpload extends Fragment {

    private static int REQUEST_CODE = 1001;
    private static final int MAX_LENGTH = 20;

    private String[] options = {"MSE", "ESE", "Resources"};
    private String exam;
    private TextView path_display;
    private Intent myFileIntent;
    private EditText courseCodeText;
    private String courseCode;

    public static FragmentUpload newIntent(){
        return new FragmentUpload();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_upload, container, false);

        // Spinner
        Spinner spinner =  v.findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exam = options[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        courseCodeText = v.findViewById(R.id.course_code);
        courseCodeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                courseCode = s.toString();
            }
        });


        // File pick
        path_display = v.findViewById(R.id.path_tester);
        Button pick_file = v.findViewById(R.id.file_pick);

        pick_file.setOnClickListener((View) ->{
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("application/pdf");
                myFileIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(myFileIntent, REQUEST_CODE);
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if (data != null) {
                Uri filePath = data.getData();
                String fileTitle = courseCode + random();
                path_display.setText(filePath.toString());
//
//                Intent uploadService = UploadUtils.newIntent(getActivity(), ,filePath.toString(),fileTitle,exam);
//                UploadUtils.enqueueWork(getActivity(), uploadService);
            }
        }
    }

    private String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

}
