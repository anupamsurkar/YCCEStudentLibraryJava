package com.syrous.yccestudentlibraryjava.ui.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelPaper;

import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.MyViewHolder> {

    private Context mContext;
    private List<ModelPaper> mData;

    public PagerAdapter(Context mContext, List<ModelPaper> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.subject_card_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = mData.get(position).getExamType() + " - " + mData.get(position).getExaminationYear();
        holder.paperName.setText(name);
        holder.courseCode.setText(mData.get(position).getUploadedBy());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView paperName;
        private TextView courseCode;
        public MyViewHolder(View itemView){
            super(itemView);
            paperName = itemView.findViewById(R.id.subname);
            courseCode = itemView.findViewById(R.id.coursecode);
        }
    }
}
