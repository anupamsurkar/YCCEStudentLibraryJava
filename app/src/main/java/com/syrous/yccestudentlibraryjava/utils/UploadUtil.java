package com.syrous.yccestudentlibraryjava.utils;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.data.ModelPaper;
import com.syrous.yccestudentlibraryjava.ui.upload.ActivityUpload;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.COURSE_CODE;
import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.DATE_FIELD;
import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.DEPARTMENT_NAME;
import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.EXAM;
import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.EXAMINATION_YEAR;
import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.NAME_FIELD;
import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.TIME_FIELD;
import static com.syrous.yccestudentlibraryjava.Constants.GlobalConstants.URL_FIELD;

/**
 * Created By : Syrous
 * date : 16/2/20
 */

public class UploadUtil extends JobIntentService {

    private static final String TAG = "UploadService";
    private static final String COLLECTION_FIELD = "reviewPapers";
    private static final String NOTIFICATION_CHANNEL = "Upload_channel";

    private StorageReference refs;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    private static final int JOB_ID = 2;

    public static void enqueueWork(Context context, Intent intent){
        enqueueWork(context, UploadUtil.class, JOB_ID, intent);
    }

    public static Intent newIntent(Context context, String serverPath, String filePath, String fileTitle, String exam,
                                   ModelPaper paper){
        Intent i =  new Intent(context, UploadUtil.class);
        i.putExtra(GlobalConstants.UPLOAD_SERVER_PATH, serverPath);
        i.putExtra(GlobalConstants.UPLOAD_FILE_TITLE, fileTitle);
        i.putExtra(GlobalConstants.UPLOAD_FILE_PATH, filePath);
        i.putExtra(GlobalConstants.EXAM_NAME, exam);
        i.putExtra("paper", paper);
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
        ModelPaper paper = (ModelPaper) intent.getSerializableExtra("paper");
        assert serverPath != null;
        refs = storage.getReference("papers/"+exam);

        if(serverPath.isEmpty()){
            String pathError = "PATH_NOT_FOUND";
            RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._ERROR_SUBJECT, pathError); // generates message of error occurred and inform ui path not found.
            return;
        }

        Uri filePath =  Uri.parse((String) intent.getSerializableExtra(GlobalConstants.UPLOAD_FILE_PATH));
        UploadTask uploadTask = refs.putFile(Objects.requireNonNull(filePath));

        Task<Uri>urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }

                // Continue with the task to get the download URL
                return refs.getDownloadUrl();

            }
        }).addOnFailureListener((task) -> {
                String firebaseError = "FIREBASE_ERROR";
                RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._ERROR_SUBJECT, firebaseError); // generates message of error occurred and inform ui to operate.
                })
                .addOnSuccessListener((task) -> {
                // TODO : Parse path and attach this utility to ui

                    Map<String, Object> data = new HashMap<>();
                    assert paper != null;
                    data.put(GlobalConstants.FILE_TITLE_FIELD,fileTitle);
                    data.put(NAME_FIELD,paper.getUploadedBy());
                    data.put(COURSE_CODE, paper.getCourseCode());
                    data.put(DEPARTMENT_NAME, paper.getDepartment());
                    data.put(EXAM, paper.getExamType());
                    data.put(EXAMINATION_YEAR, paper.getExaminationYear());
                    data.put(DATE_FIELD, paper.getUploadDate());
                    data.put(TIME_FIELD, paper.getUploadTime());
                    data.put(URL_FIELD,task.toString());

                    db.collection(serverPath).document(fileTitle).set(data).addOnSuccessListener((task1) -> {
                        String success = "DOCUMENT_UPLOADED_TO_FIRESTORE";
                        RxEventBus.getBus(getApplicationContext()).publish(RxEventBus._PROGRESS_SUBJECT, success); // generate message of success, task downloadUrl and store it in database and fireStore and inform ui to move to next state.
                    });

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
