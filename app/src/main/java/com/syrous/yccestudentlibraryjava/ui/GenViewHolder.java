package com.syrous.yccestudentlibraryjava.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        intent = new Intent(mContext, SubjectActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
