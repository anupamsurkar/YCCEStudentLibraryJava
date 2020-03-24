package com.syrous.yccestudentlibraryjava.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.syrous.yccestudentlibraryjava.data.ModelPaper;

import java.io.File;

import static androidx.core.content.FileProvider.getUriForFile;

public class DownloadAndSaveUtil extends JobIntentService {

    public static final String DOWNLOAD_PAPER = "DOWNLOAD_PAPER";
    private static final int JOB_ID = 3;

    private FirebaseStorage storage;
    private FirebaseFirestore db;

    public static void enqueueWork(Context context, Intent intent){
        enqueueWork(context, DownloadAndSaveUtil.class, JOB_ID, intent);
    }

    public static Intent newIntent(Context context, ModelPaper paper){
        Intent intent = new Intent(context, DownloadAndSaveUtil.class);
        intent.putExtra(DOWNLOAD_PAPER, paper);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        ModelPaper paper = (ModelPaper) intent.getSerializableExtra(DOWNLOAD_PAPER);
        StorageReference refs = storage.getReferenceFromUrl(paper.getDownloadUrl());

        String filePath = paper.getExamType();
        String filename = paper.getPaperTitle() + ".pdf";
        String storageLocation = getExternalFilesDir(filePath).toString();
        Log.d("FilePath", storageLocation);
        File localFile = new File(getExternalFilesDir(filePath), filename);

//        refs.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
//        });


        Uri fileUri = getUriForFile(this, getPackageName()+".fileprovider", localFile);

        int uid = Binder.getCallingUid();
        String callingPackage = getPackageManager().getNameForUid(uid);

        getApplication().grantUriPermission(callingPackage, fileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Log.d("FilePath", fileUri.toString());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(fileUri, getContentResolver().getType(fileUri));
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }
}
