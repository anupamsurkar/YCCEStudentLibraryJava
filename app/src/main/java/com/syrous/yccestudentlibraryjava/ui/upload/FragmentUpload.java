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
import com.syrous.yccestudentlibraryjava.utils.UploadUtil;

import java.util.Random;

public class FragmentUpload extends Fragment {

    private static int REQUEST_CODE = 1001;
    private static final int MAX_LENGTH = 20;

    private String[] options = { "MSE1", "MSE2", "MSE3", "ESE", "Resources"};
    private String[] dept = {"CT", "CV", "EE", "EL", "ET", "FY", "IT", "ME"};
    private String[] sem = {"sem1", "sem2", "sem3", "sem4", "sem5", "sem6", "sem7", "sem8"};
    private String exam;
    private String examType;
    private String department;
    private String semester;
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
        Spinner spinner1 =  v.findViewById(R.id.spinner1);
        Spinner spinner2 =  v.findViewById(R.id.spinner2);
        Spinner spinner3 =  v.findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                examType = options[position].toLowerCase();
                if(!(examType.equals("ese") || examType.equals("resources"))){
                    exam = "mse";
                } else {
                    exam = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter1= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, dept);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = dept[position].toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter3= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, sem);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semester = sem[position].toLowerCase();
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

            if(department.equals("fy") && (!semester.equals("sem1") || !semester.equals("sem2"))) {

            } else if(!department.equals("fy") && (semester.equals("sem1") || semester.equals("sem2"))) {

            } else {
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("application/pdf");
                myFileIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(myFileIntent, REQUEST_CODE);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if (data != null) {
                Uri filePath = data.getData();
                String fileTitle = examType + "_" + courseCode + "_" + random();
                path_display.setText("Uploading to "+department+"/"+semester+"/"+courseCode+"/"+exam+"/"+examType+"/"+fileTitle);
                String serverPath = "paperRefs/"+department+"/"+semester+"/"+courseCode+"/"+exam;
                assert filePath != null;
                Intent uploadService = UploadUtil.newIntent(getActivity(), serverPath,filePath.toString(),fileTitle,exam, examType);
                UploadUtil.enqueueWork(getActivity(), uploadService);
            }
        }
    }

    private String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(10) + 97);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

}
