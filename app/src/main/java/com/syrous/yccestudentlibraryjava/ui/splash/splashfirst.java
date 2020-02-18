package com.syrous.yccestudentlibraryjava.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.login.LoginActivity;

public class splashfirst extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splashfirst);

        handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent=new Intent(splashfirst.this, LoginActivity.class);
            startActivity(intent);
            finish();
        },2500);

    }
}
