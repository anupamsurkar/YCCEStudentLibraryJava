package com.syrous.yccestudentlibraryjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CtechAdapter extends RecyclerView.Adapter<CtechAdapter.MyViewHolder> {

    Context context;
    String[] semname;

    public CtechAdapter(Context context, String[] semname) {
        this.context = context;
        this.semname = semname;
    }
    @NonNull
    @Override
    public CtechAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.ctech_sem_card, parent, false);
        return new CtechAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CtechAdapter.MyViewHolder holder, int position) {


        holder.textView.setText(semname[position]);


    }

    @Override
    public int getItemCount() {
        return semname.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView= itemView.findViewById(R.id.ctech_sem_num);


        }
    }
}
