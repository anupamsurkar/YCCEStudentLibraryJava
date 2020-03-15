package com.syrous.yccestudentlibraryjava.ui.subject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelSubject;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubject extends AppCompatActivity {

    private String path;
    private List<ModelSubject> subjects;
    private RecyclerView subjectRecycler;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        subjects = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        String dept = getIntent().getStringExtra(GlobalConstants.DEPARTMENT_NAME);
        String sem = getIntent().getStringExtra(GlobalConstants.SEMESTER);
        path = "paperRefs/" + dept + "/" + sem;


        db.collection(path)
                .get()
                .addOnSuccessListener(task -> {
                    subjects = new ArrayList<>();
                    for (DocumentSnapshot document : task.getDocuments()) {
                        ModelSubject subject = new ModelSubject(document.getId(), (String) document.get("course_name"));
                        subjects.add(subject);
                    }
                    Log.d("DOWNLOAD", "Downloaded size : "+subjects.size());
                    subjectRecycler = findViewById(R.id.subjectRecycler);
                    subjectRecycler.setLayoutManager(new LinearLayoutManager(this));
                    subjectRecycler.setAdapter(new SubjectAdapter(this, subjects));
                });
    }

}
