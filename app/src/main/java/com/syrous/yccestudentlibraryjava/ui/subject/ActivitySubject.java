package com.syrous.yccestudentlibraryjava.ui.subject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelSubject;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubject extends AppCompatActivity {

    private String dept;
    private String sem;
    private RecyclerView subjectRecycler;
    private FirebaseFirestore db;
    private List<ModelSubject> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        subjects = new ArrayList<>();
        dept = getIntent().getStringExtra(GlobalConstants.DEPARTMENT_NAME);
        sem = getIntent().getStringExtra(GlobalConstants.SEMESTER);
        String path = "paperRefs/" + dept + "/" + sem;

        subjectRecycler = findViewById(R.id.subjectRecycler);
        subjectRecycler.setLayoutManager(new LinearLayoutManager(this));
        subjectRecycler.setAdapter(new SubjectAdapter(this, subjects));
    }
}
