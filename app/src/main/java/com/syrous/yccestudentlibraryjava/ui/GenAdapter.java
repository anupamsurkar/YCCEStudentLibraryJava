package com.syrous.yccestudentlibraryjava.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created By : Syrous
 * date : 15/2/20
 */

public class GenAdapter extends RecyclerView.Adapter<GenViewHolder> {

    private Context context;
    private String[] semName;
    @LayoutRes
    private int layoutResId;
    @IdRes
    private int textViewId;

    public GenAdapter(Context context, String[] semName, @LayoutRes int layoutResId, @IdRes int textViewId){
        this.context = context;
        this.semName = semName;
        this.layoutResId = layoutResId;
        this.textViewId = textViewId;
    }

    @NonNull
    @Override
    public GenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutResId, parent, false);
        return new GenViewHolder(view, textViewId, context);
    }

    @Override
    public void onBindViewHolder(@NonNull GenViewHolder holder, int position) {
        holder.setTextView(semName[position]);
    }

    @Override
    public int getItemCount() {
        return semName.length;
    }
}
