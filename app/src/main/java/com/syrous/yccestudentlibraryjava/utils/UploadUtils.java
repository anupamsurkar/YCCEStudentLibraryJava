package com.syrous.yccestudentlibraryjava.utils;

import android.app.IntentService;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.HandlerThread;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;

import java.util.Objects;

/**
 * Created By : Syrous
 * date : 16/2/20
 */

public class UploadUtils extends IntentService {

    private static final String TAG = "UploadService";
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference refs = storage.getReference();

    public UploadUtils() {
        super(TAG);
    }

    public static Intent newIntent(Context context){
        return new Intent(context, UploadUtils.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(!isNetworkAvailableAndConnected()){
            //TODO : create broadcast receiver at frontend to receive signals.
            return;
        }

        String path = intent.getStringExtra(GlobalConstants.UPLOAD_PATH);
        if(path.isEmpty()){
            //TODO : generate message of error occurred and inform ui to operate.
            return;
        }

        Uri filePath = intent.getData();
        refs.putFile(Objects.requireNonNull(filePath)).addOnFailureListener(e -> {
             //TODO : generate message of error occurred and inform ui to operate.
        }).addOnCompleteListener(task -> {
            //TODO : generate message of success, task downloadUrl and store it in database and fireStore and inform ui to move to next state.
        }).addOnProgressListener(taskSnapshot -> {
            //TODO : To inform ui about progress.
        }).addOnPausedListener(taskSnapshot -> {
            //TODO : To inform ui, task is paused.
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
