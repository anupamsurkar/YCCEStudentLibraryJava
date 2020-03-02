package com.syrous.yccestudentlibraryjava.ui.upload;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created By : Syrous
 * date : 2/3/20
 */
public class ActivityUpload extends AppCompatActivity {

    public static Intent newIntent(Context context){
        Intent uploadIntent = new Intent(context, ActivityUpload.class);
        return uploadIntent;
    }


}
