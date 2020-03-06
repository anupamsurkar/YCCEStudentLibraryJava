package com.syrous.yccestudentlibraryjava.utils;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.ui.upload.ActivityUpload;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created By : Syrous
 * date : 16/2/20
 */

public class UploadUtils extends JobIntentService {

    private static final String TAG = "UploadService";
    private static final String NAME_FIELD =  "uploaded-by";
    private static final String DATE_FIELD = "upload-date";
    private static final String FILE_TITLE_FIELD = "paper-title";
    private static final String EXAM_FIELD = "exam";
    private static final String DEPARTMENT_FIELD = "department";
    private static final String URL_FIELD = "download-url";
    private static final String COLLECTION_FIELD = "reviewPapers";
    private static final String NOTIFICATION_CHANNEL = "Upload_channel";

    private StorageReference refs;
    private boolean mSaved = false;
    private FirebaseFirestore db;
    public UploadUtils() {

    }

    public static Intent newIntent(Context context, String path, Uri data){
        Intent i =  new Intent(context, UploadUtils.class);
        i.putExtra(GlobalConstants.UPLOAD_PATH, path);
        i.putExtra("Upload_URI", data);
        return i;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(!isNetworkAvailableAndConnected()){
            String netError = "NETWORK_NOT_CONNECTED";
            RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._ERROR_SUBJECT, netError); // Inform Ui Network in not available
        }
        Intent notificationIntent = ActivityUpload.newIntent(this);
        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent,0);
        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
                                        .setContentTitle("Paper Uploading")
                                        .setContentText("")
                                        .setContentIntent(pi)
                                        .build();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        refs = storage.getReference();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        db = FirebaseFirestore.getInstance();
        String path = Objects.requireNonNull(intent).getStringExtra(GlobalConstants.UPLOAD_PATH);
        assert path != null;
        if(path.isEmpty()){
            String pathError = "PATH_NOT_FOUND";
            RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._ERROR_SUBJECT, pathError); // generates message of error occurred and inform ui path not found.
            return;
        }

        Uri filePath = intent.getData();
        UploadTask uploadTask = refs.putFile(Objects.requireNonNull(filePath));
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String firebaseError = "FIREBASE_ERROR";
                RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._ERROR_SUBJECT, firebaseError); // generates message of error occurred and inform ui to operate.
            }
        })
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                // TODO : Parse path and attack this utility to ui
                String parsePath = path;
                Map<String, Object>data = new HashMap<>();
                data.put(FILE_TITLE_FIELD,"");
                data.put(EXAM_FIELD,"");
                data.put(DEPARTMENT_FIELD, "");
                data.put(NAME_FIELD, "");
                data.put(DATE_FIELD, "");
                data.put(URL_FIELD, "");

               db.collection(path).add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentReference> task) {
                       String success = "DOCUMENT_UPLOADED_TO_FIRESTORE";
                        RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._PROGRESS_SUBJECT, success); // generate message of success, task downloadUrl and store it in database and fireStore and inform ui to move to next state.
                   }
               });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = taskSnapshot.getBytesTransferred();
                Uri uploadSessionUri = taskSnapshot.getUploadSessionUri();
                if(uploadSessionUri != null && !mSaved){
                     SharedPreferences pref = getSharedPreferences(GlobalConstants.UPLOAD_SESSION, 0);
                     pref.edit()
                         .putString(GlobalConstants.UPLOAD_SESSION_KEY, uploadSessionUri.toString())
                         .putBoolean(GlobalConstants.UPLOAD_SESSION_SAVED, mSaved)
                         .apply();
                }
                long bytesToUpload = taskSnapshot.getTotalByteCount();

                RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._PROGRESS_SUBJECT, progress); // To inform ui about progress.
            }
        }).addOnPausedListener(taskSnapshot -> {
            SharedPreferences prefs = getSharedPreferences(GlobalConstants.UPLOAD_SESSION, 0);
            prefs.edit()
                    .putString(GlobalConstants.UPLOAD_REFERENCE, refs.toString())
                    .apply();

            String pausedUpload = "UPLOAD_PAUSED";
            RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._PROGRESS_SUBJECT, pausedUpload); // To inform ui, task is paused.
        });
    }


    @Override
    public boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = Objects.requireNonNull(cm).getActiveNetworkInfo() != null;

        return isNetworkAvailable &&
                Objects.requireNonNull(cm.getActiveNetworkInfo()).isConnected();
    }
}
