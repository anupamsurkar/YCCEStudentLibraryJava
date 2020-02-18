package com.syrous.yccestudentlibraryjava.ui;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created By : Syrous
 * date : 14/2/20
 */

public class GenViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    @SuppressLint("ResourceType")
    public GenViewHolder(@NonNull View itemView, @IdRes int LayoutResId) {
        super(itemView);
        mTextView = itemView.findViewById(LayoutResId);
    }

    public void setTextView(String text){
        mTextView.setText(text);
    }
}
