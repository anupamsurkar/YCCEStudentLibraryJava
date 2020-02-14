package com.syrous.yccestudentlibraryjava.ui.el;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syrous.yccestudentlibraryjava.R;

public class ElectricalAdapter extends RecyclerView.Adapter<ElectricalAdapter.MyViewHolder> {

    Context context;
    String[] semname;

    public ElectricalAdapter(Context context, String[] semname) {
        this.context = context;
        this.semname = semname;
    }
    @NonNull
    @Override
    public ElectricalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.el_sem_card, parent, false);
        return new ElectricalAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectricalAdapter.MyViewHolder holder, int position) {


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

            textView= itemView.findViewById(R.id.electrical_sem_num);


        }
    }
}
