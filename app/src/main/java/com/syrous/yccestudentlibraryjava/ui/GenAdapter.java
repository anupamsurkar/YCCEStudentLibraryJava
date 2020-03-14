package com.syrous.yccestudentlibraryjava.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.subject.ActivitySubject;

/**
 * Created By : Syrous
 * date : 15/2/20
 */

public class GenAdapter extends RecyclerView.Adapter<GenAdapter.GenViewHolder> {

    private Context context;
    private String dept;
    private String[] semName;
    private String[] semShortName;
    @LayoutRes
    private int layoutResId;
    @IdRes
    private int textViewId;

    public GenAdapter(Context context, String dept, @LayoutRes int layoutResId, @IdRes int textViewId){
        this.context = context;
        this.layoutResId = layoutResId;
        this.textViewId = textViewId;
        this.dept = dept;

        if(dept.equals("fy")) {
            semName = context.getResources().getStringArray(R.array.semesters_fy);
            semShortName = context.getResources().getStringArray(R.array.sem_name_fy);
        } else {
            semName = context.getResources().getStringArray(R.array.semesters);
            semShortName = context.getResources().getStringArray(R.array.sem_name);
        }
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

    public class GenViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private  Context mContext;
        private TextView mTextView;

        @SuppressLint("ResourceType")
        public GenViewHolder(@NonNull View itemView, @IdRes int LayoutResId, Context context) {
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
            intent = new Intent(mContext, ActivitySubject.class);
            intent.putExtra(GlobalConstants.SEMESTER, semShortName[getAdapterPosition()]);
            intent.putExtra(GlobalConstants.DEPARTMENT_NAME, dept);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }

}
