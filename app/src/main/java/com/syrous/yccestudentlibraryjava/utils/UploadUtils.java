package com.syrous.yccestudentlibraryjava.utils;

import android.app.IntentService;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.FileUtils;
import android.os.HandlerThread;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created By : Syrous
 * date : 16/2/20
 */

public class UploadUtils extends IntentService {


    private static final String TAG = "UploadService";
    private static final String NAME_FIELD =  "uploaded-by";
    private static final String DATE_FIELD = "upload-date";
    private static final String FILE_TITLE_FIELD = "paper-title";
    private static final String EXAM_FIELD = "exam";
    private static final String DEPARTMENT_FIELD = "department";
    private static final String URL_FIELD = "download-url";

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference refs = storage.getReference();
    private UploadTask uploadTask;
    private boolean mSaved = false;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public UploadUtils() {
        super(TAG);
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
            //TODO: Inform Ui Network in not available
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(!isNetworkAvailableAndConnected()){
            //TODO : create broadcast receiver at frontend to receive signals.
            return;
        }

        String path = Objects.requireNonNull(intent).getStringExtra(GlobalConstants.UPLOAD_PATH);
        assert path != null;
        if(path.isEmpty()){
            //TODO : generate message of error occurred and inform ui to operate.
            return;
        }


        Uri filePath = intent.getData();
        uploadTask = refs.putFile(Objects.requireNonNull(filePath));
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //TODO : generate message of error occurred and inform ui to operate.
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                //TODO : generate message of success, task downloadUrl and store it in database and fireStore and inform ui to move to next state.
                Map<String, Object>data = new HashMap<>();
                data.put(FILE_TITLE_FIELD,"");
                data.put(EXAM_FIELD,"");
                data.put(DEPARTMENT_FIELD, "");
                data.put(NAME_FIELD, "");
                data.put(DATE_FIELD, "");
                data.put(URL_FIELD, "");

                
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                //TODO : To inform ui about progress.
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
            }
        }
        ).addOnPausedListener(taskSnapshot -> {
            //TODO : To inform ui, task is paused.
            SharedPreferences prefs = getSharedPreferences(GlobalConstants.UPLOAD_SESSION, 0);
            prefs.edit()
                    .putString(GlobalConstants.UPLOAD_REFERENCE, refs.toString())
                    .apply();
        });
    }


    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }
}
