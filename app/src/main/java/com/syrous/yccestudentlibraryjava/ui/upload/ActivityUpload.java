package com.syrous.yccestudentlibraryjava.ui.upload;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.ui.SingleFragmentActivity;

/**
 * Created By : Syrous
 * date : 2/3/20
 */
public class ActivityUpload extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent uploadIntent = new Intent(context, ActivityUpload.class);
        return uploadIntent;
    }

    @Override
    protected Fragment createFragment() {
        return FragmentUpload.newIntent();
    }

}
