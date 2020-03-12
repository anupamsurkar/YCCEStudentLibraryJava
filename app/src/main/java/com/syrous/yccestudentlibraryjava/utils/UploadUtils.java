package com.syrous.yccestudentlibraryjava.utils;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.data.User;
import com.syrous.yccestudentlibraryjava.ui.upload.ActivityUpload;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
    private static final String TIME_FIELD = "upload-time";
    private static final String FILE_TITLE_FIELD = "paper-title";
    private static final String EXAM_FIELD = "exam";
    private static final String DEPARTMENT_FIELD = "department";
    private static final String URL_FIELD = "download-url";
    private static final String COLLECTION_FIELD = "reviewPapers";
    private static final String NOTIFICATION_CHANNEL = "Upload_channel";

    private StorageReference refs;
    private boolean mSaved = false;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    private static final int JOB_ID = 2;

    public static void enqueueWork(Context context, Intent intent){
        enqueueWork(context, UploadUtils.class, JOB_ID, intent);
    }

    public static Intent newIntent(Context context, String serverPath, String filePath, String fileTitle, String exam){
        Intent i =  new Intent(context, UploadUtils.class);
        i.putExtra(GlobalConstants.UPLOAD_SERVER_PATH, serverPath);
        i.putExtra(GlobalConstants.UPLOAD_FILE_TITLE, fileTitle);
        i.putExtra(GlobalConstants.UPLOAD_FILE_PATH, filePath);
        i.putExtra(GlobalConstants.EXAM_NAME, exam);
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
//        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
//                                        .setContentTitle("Paper Uploading")
//                                        .setContentText("Uploading the Selected Paper")
//                                        .setContentIntent(pi)
//                                        .build();

        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        String exam = intent.getStringExtra(GlobalConstants.EXAM_NAME);
        String fileTitle = intent.getStringExtra(GlobalConstants.UPLOAD_FILE_TITLE);
        String serverPath = Objects.requireNonNull(intent).getStringExtra(GlobalConstants.UPLOAD_SERVER_PATH);
        assert serverPath != null;
        refs = storage.getReference("papers/"+exam+fileTitle);

        if(serverPath.isEmpty()){
            String pathError = "PATH_NOT_FOUND";
            RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._ERROR_SUBJECT, pathError); // generates message of error occurred and inform ui path not found.
            return;
        }

        Uri filePath =  Uri.parse((String) intent.getSerializableExtra(GlobalConstants.UPLOAD_FILE_PATH));
        UploadTask uploadTask = refs.putFile(Objects.requireNonNull(filePath));
        uploadTask.addOnFailureListener((task) -> {
                String firebaseError = "FIREBASE_ERROR";
                RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._ERROR_SUBJECT, firebaseError); // generates message of error occurred and inform ui to operate.
                })
                .addOnSuccessListener((task) -> {
                // TODO : Parse path and attach this utility to ui
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                    Map<String, Object> data = new HashMap<>();
                    data.put(FILE_TITLE_FIELD,"");
                    data.put(EXAM_FIELD,"");
                    data.put(DEPARTMENT_FIELD, "");
                    data.put(NAME_FIELD, User.get(getApplicationContext()).getUser().getUserName());
                    data.put(DATE_FIELD, currentDate);
                    data.put(TIME_FIELD, currentTime);
                    data.put(URL_FIELD,refs.getDownloadUrl());

                    db.collection(serverPath).add(data).addOnSuccessListener((task1) -> {
                        String success = "DOCUMENT_UPLOADED_TO_FIRESTORE";
                        RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._PROGRESS_SUBJECT, success); // generate message of success, task downloadUrl and store it in database and fireStore and inform ui to move to next state.
                    });

                }).addOnProgressListener((taskSnapshot) -> {
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
