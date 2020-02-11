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

public class ElectronicsAdapter extends RecyclerView.Adapter<ElectronicsAdapter.MyViewHolder> {

    Context context;
    String[] semname;

    public ElectronicsAdapter(Context context, String[] semname) {
        this.context = context;
        this.semname = semname;
    }
    @NonNull
    @Override
    public ElectronicsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.electronics_sem_card, parent, false);
        return new ElectronicsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectronicsAdapter.MyViewHolder holder, int position) {


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

            textView= itemView.findViewById(R.id.electronics_sem_num);


        }
    }
}
