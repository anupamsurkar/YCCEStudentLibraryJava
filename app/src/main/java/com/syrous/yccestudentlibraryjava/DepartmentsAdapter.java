package com.syrous.yccestudentlibraryjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.MyVIewHolder> {


    private Context mContext;
    private List<Departments> mdata;

    public DepartmentsAdapter(Context mContext, List<Departments> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflator= LayoutInflater.from(mContext);
        view= mInflator.inflate(R.layout.home_cardview, parent, false);



        return new MyVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVIewHolder holder, int position) {
        //holder.dept_title.setText(mdata.get(position).ge);

        holder.dept_thumbnail.setImageResource(mdata.get(position).getThumbnail());
        //holder.dept_title.setText(mdata.get(position).);



    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyVIewHolder extends RecyclerView.ViewHolder{

        TextView dept_title;
        ImageView dept_thumbnail;

        public MyVIewHolder(@NonNull View itemView) {
            super(itemView);

            dept_title= (TextView) itemView.findViewById(R.id.dept_name);
            dept_thumbnail= (ImageView) itemView.findViewById(R.id.img_id);





        }
    }
}
