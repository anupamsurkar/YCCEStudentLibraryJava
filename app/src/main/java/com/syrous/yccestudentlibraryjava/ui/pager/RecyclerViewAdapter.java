package com.syrous.yccestudentlibraryjava.ui.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;

import java.awt.font.TextAttribute;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<paper_tabs> mdata;

    public RecyclerViewAdapter(Context mContext, List<paper_tabs> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.subject_card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.paperName.setText(mdata.get(position).getPapernaame());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView paperName;

        public MyViewHolder(View itemView){
            super(itemView);
            paperName = (TextView) itemView.findViewById(R.id.subname);

        }
    }
}
