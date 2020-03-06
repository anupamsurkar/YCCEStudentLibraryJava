package com.syrous.yccestudentlibraryjava.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created By : Syrous
 * date : 2/3/20
 */

public class DownloadUtils extends JobIntentService {

    private static String TAG = "DownloadUtils";
    private FirebaseFirestore db;
    private DocumentReference docRef;
    private List<ModelPapers> papers;

    public DownloadUtils(){

    }

    public static Intent newIntent(Context context){
        Intent i = new Intent(context, DownloadUtils.class);
        return i;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseFirestore.getInstance();
        papers = new ArrayList<>();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        db.collection("paperRefs/ct/sem6")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                            Log.d(TAG, "Document Fetched : "+document.getId());
                        }
                    } else {
                        Log.d(TAG, "Error in Getting Documents ", task.getException());
                    }
                });
    }
}
