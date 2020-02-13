package com.syrous.yccestudentlibraryjava;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EtcAdapter extends RecyclerView.Adapter<EtcAdapter.MyViewHolder> {

    Context context;
    String[] semname;

    public EtcAdapter(Context context, String[] semname) {
        this.context = context;
        this.semname = semname;
    }
    @NonNull
    @Override
    public EtcAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.et_sem_card, parent, false);
        return new EtcAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtcAdapter.MyViewHolder holder, int position) {


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

            textView= itemView.findViewById(R.id.etc_sem_num);


        }
    }
}
