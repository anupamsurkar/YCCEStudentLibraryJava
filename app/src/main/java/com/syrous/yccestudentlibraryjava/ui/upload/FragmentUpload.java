package com.syrous.yccestudentlibraryjava.ui.upload;

import android.annotation.SuppressLint;
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
import com.syrous.yccestudentlibraryjava.data.ModelPaper;
import com.syrous.yccestudentlibraryjava.data.User;
import com.syrous.yccestudentlibraryjava.utils.UploadUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class FragmentUpload extends Fragment {

    private static int REQUEST_CODE = 1001;
    private static final int MAX_LENGTH = 20;

    private Intent myFileIntent;
    private TextView path_display;
    private Button pickFile;

    private String[] options = { "MSE1", "MSE2", "MSE3", "ESE", "Resources"};
    private String[] dept = {"CT", "CV", "EE", "EL", "ET", "FY", "IT", "ME"};
    private String[] sem = {"sem1", "sem2", "sem3", "sem4", "sem5", "sem6", "sem7", "sem8"};
    private String exam;
    private String examType;
    private String department;
    private String semester;
    private String courseCode;
    private String year;

    static FragmentUpload newIntent(){
        return new FragmentUpload();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_upload, container, false);

        // Spinner
        Spinner examSpinner =  v.findViewById(R.id.exam_spinner);
        Spinner departmentSpinner =  v.findViewById(R.id.dept_spinner);
        Spinner semSpinner =  v.findViewById(R.id.sem_spinner);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(Objects.requireNonNull(getActivity()),android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        examSpinner.setAdapter(adapter);
        examSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        ArrayAdapter<String> departmentAdapter= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, dept);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = dept[position].toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> semAdapter= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, sem);
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semSpinner.setAdapter(semAdapter);
        semSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semester = sem[position].toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EditText yearText = v.findViewById(R.id.year_edit_text);
        yearText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 year = s.toString();
            }
        });


        EditText courseCodeText = v.findViewById(R.id.course_code);
        courseCodeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                courseCode = s.toString().toLowerCase();
            }
        });


        // File pick
        path_display = v.findViewById(R.id.path_tester);
        pickFile = v.findViewById(R.id.file_pick);
        pickFile.setOnClickListener((View) ->{

            if(department.equals("fy") && (!semester.equals("sem1") || !semester.equals("sem2"))) {

            } else if(!department.equals("fy") && (semester.equals("sem1") || semester.equals("sem2"))) {

            } else {
               pickFile.setText("Uploading ...");
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("application/pdf");
                myFileIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(myFileIntent, REQUEST_CODE);
            }
        });

        return v;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if (data != null) {
                Uri filePath = data.getData();
                String fileTitle = examType + "_" + courseCode + "_" + random();
                String serverPath = "paperRefs/"+department+"/"+semester+"/"+courseCode+"/"+exam;

                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                path_display.setText("Uploading to "+department+"/"+semester+"/"+courseCode+"/"+exam+"/"+examType+"/"+fileTitle);

                ModelPaper paper = new ModelPaper(fileTitle, department, courseCode, examType,
                        User.get(getActivity()).getUser().getUserName(), currentDate,null,
                        currentTime, Integer.parseInt(year));

                assert filePath != null;
                Intent uploadService = UploadUtil.newIntent(getActivity(), serverPath,
                        filePath.toString(), fileTitle, exam, paper);

                UploadUtil.enqueueWork(getActivity(), uploadService);
                pickFile.setText("CHOOSE DOCUMENT");
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
