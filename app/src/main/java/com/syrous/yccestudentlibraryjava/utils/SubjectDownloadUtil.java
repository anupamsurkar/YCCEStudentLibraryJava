package com.syrous.yccestudentlibraryjava.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.data.ModelSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created By : Syrous
 * date : 2/3/20
 */

public class SubjectDownloadUtil extends JobIntentService {

    private static String TAG = "DownloadUtils";

    private FirebaseFirestore db;
    private DocumentReference docRef;
    private List<ModelSubject> subjects;

    public static Intent newIntent(Context context, String serverPath){
        Intent i = new Intent(context, SubjectDownloadUtil.class);
        i.putExtra(GlobalConstants.DOWNLOAD_SERVER_PATH, serverPath);
        return i;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseFirestore.getInstance();
        subjects = new ArrayList<>();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        String path = intent.getStringExtra(GlobalConstants.DOWNLOAD_SERVER_PATH);
        assert path != null;
        db.collection(path)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                            if(document.get("course_name") != null) {
                                ModelSubject subject = new ModelSubject(document.getId(), document.get("course_name").toString());
                                subjects.add(subject);
                            }
                        }
                    } else {
                        Log.d(TAG, "Error in Getting Documents ", task.getException());
                    }
                });
    }
}
