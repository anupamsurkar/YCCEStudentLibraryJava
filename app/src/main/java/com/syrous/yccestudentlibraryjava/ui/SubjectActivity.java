package com.syrous.yccestudentlibraryjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.syrous.yccestudentlibraryjava.R;

public class SubjectActivity extends AppCompatActivity {

    String subjectName[], courseCode[];
    RecyclerView subjectrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        subjectName= getResources().getStringArray(R.array.Subjects);
        courseCode=getResources().getStringArray(R.array.course_code);

        subjectrecycler= findViewById(R.id.subjectRecycler);

        SubjectAdapter subjectAdapter= new SubjectAdapter(this, subjectName, courseCode );
        subjectrecycler.setAdapter(subjectAdapter);
        subjectrecycler.setLayoutManager(new LinearLayoutManager(this));








    }
}
