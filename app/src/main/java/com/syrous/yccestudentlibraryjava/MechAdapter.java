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

public class MechAdapter extends RecyclerView.Adapter<MechAdapter.MyViewHolder> {

    Context context;
    String[] semname;

    public MechAdapter(Context context, String[] semname) {
        this.context = context;
        this.semname = semname;
    }
    @NonNull
    @Override
    public MechAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.mech_sem_card, parent, false);
        return new MechAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MechAdapter.MyViewHolder holder, int position) {


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

            textView= itemView.findViewById(R.id.mech_sem_num);


        }
    }
}
