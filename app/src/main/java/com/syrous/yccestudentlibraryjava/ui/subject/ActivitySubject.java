package com.syrous.yccestudentlibraryjava.ui.subject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        TextView textView = findViewById(R.id.toolbar_subjects);

        //Loading

        ProgressBar progressBar = findViewById(R.id.progressBar2);
        subjectRecycler = findViewById(R.id.subjectRecycler);
        subjectRecycler.setVisibility(View.GONE);

        //
        subjects = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        String dept = getIntent().getStringExtra(GlobalConstants.DEPARTMENT_NAME);
        String sem = getIntent().getStringExtra(GlobalConstants.SEMESTER);
        textView.setText(dept);

        Toolbar toolbar = findViewById(R.id.toolbar3);


        switch(dept) {
            case "ct":
                textView.setText(R.string.ctech);
                toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_ct));
                break;
            case "cv":
                textView.setText(R.string.civil);
                toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolabr_cv));
                break;
            case "ee":
                textView.setText(R.string.electronics);
                toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_ee));
                break;
            case "el":
                textView.setText(R.string.electrical);
                toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_el));
                break;
            case "et":
                textView.setText(R.string.etc);
                toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_etc));
                break;
            case "fy":
                textView.setText(R.string.fy);
                toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_fy));
                break;
            case "it":
                textView.setText(R.string.it);
                toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.toolbar_it));
                break;
            case "me":
                textView.setText(R.string.mechanical);
                toolbar.setBackground(ContextCompat.getDrawable(this,R.drawable.toolbar_me));
                break;
        }

        String tv = (String) textView.getText();
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

                    subjectRecycler.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    subjectRecycler.setLayoutManager(new LinearLayoutManager(this));
                    subjectRecycler.setAdapter(new SubjectAdapter(this, subjects, path,tv));
                });
    }

}
