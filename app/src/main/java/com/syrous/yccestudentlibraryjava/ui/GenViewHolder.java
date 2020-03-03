package com.syrous.yccestudentlibraryjava.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.civil.ActivityCvHome;
import com.syrous.yccestudentlibraryjava.ui.pager.ActivityPager;

/**
 * Created By : Syrous
 * date : 14/2/20
 */

public class GenViewHolder extends RecyclerView.ViewHolder
implements View.OnClickListener{

    private  Context mContext;
    private TextView mTextView;

    @SuppressLint("ResourceType")
    public GenViewHolder(@NonNull View itemView, @IdRes int LayoutResId,Context context) {
        super(itemView);
        this.mContext = context;
        mTextView = itemView.findViewById(LayoutResId);
        itemView.setOnClickListener(this);
    }

    public void setTextView(String text){
        mTextView.setText(text);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        intent = new Intent(mContext, ActivityPager.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        mContext.startActivity(intent);
    }
}
