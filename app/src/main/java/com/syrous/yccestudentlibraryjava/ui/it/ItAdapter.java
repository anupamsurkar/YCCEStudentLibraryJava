package com.syrous.yccestudentlibraryjava.ui.it;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syrous.yccestudentlibraryjava.R;

public class ItAdapter extends RecyclerView.Adapter<ItAdapter.MyViewHolder> {

    Context context;
    String[] semname;
    public ItAdapter(Context context, String[] semname) {
        this.context = context;
        this.semname = semname;
    }


    @NonNull
    @Override
    public ItAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.it_sem_card, parent, false);
        return new ItAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItAdapter.MyViewHolder holder, int position) {


        holder.textView.setText(semname[position]);


    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView= itemView.findViewById(R.id.it_sem_num);

        }
    }
}