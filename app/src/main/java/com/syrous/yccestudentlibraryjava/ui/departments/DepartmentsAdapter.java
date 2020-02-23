package com.syrous.yccestudentlibraryjava.ui.departments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.civil.ActivityCvHome;
import com.syrous.yccestudentlibraryjava.ui.ctech.ActivityCtHome;
import com.syrous.yccestudentlibraryjava.ui.ee.ActivityEeHome;
import com.syrous.yccestudentlibraryjava.ui.el.ActivityElHome;
import com.syrous.yccestudentlibraryjava.ui.etc.ActivityEtHome;
import com.syrous.yccestudentlibraryjava.ui.it.ActivityItHome;
import com.syrous.yccestudentlibraryjava.ui.mech.ActivityMechHome;

import java.util.List;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentHolder> {


    private Context mContext;
    private List<ModelDepartments> mData;

    public DepartmentsAdapter(Context mContext, List<ModelDepartments> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public DepartmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.home_cardview, parent, false);
        return new DepartmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentHolder holder, int position) {
        holder.setThumbnail(mData.get(position).getThumbnail());
        holder.setTextView(mData.get(position).getDept());

        holder.itemView.setOnClickListener((View) -> {
            if(position == 0){
                Intent intent = new Intent(mContext, ActivityCvHome.class);
                mContext.startActivity(intent);
            }
            if(position == 1){
                Intent intent = new Intent(mContext, ActivityCtHome.class);
                mContext.startActivity(intent);
            }
            if(position == 2){
                Intent intent = new Intent(mContext, ActivityCvHome.class);
                mContext.startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(mContext, ActivityElHome.class);
                mContext.startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(mContext, ActivityEtHome.class);
                mContext.startActivity(intent);
            }
            if(position == 5){
                Intent intent = new Intent(mContext, ActivityEeHome.class);
                mContext.startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(mContext, ActivityMechHome.class);
                mContext.startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(mContext, ActivityItHome.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class DepartmentHolder extends RecyclerView.ViewHolder{

    private TextView dept_title;
    private ImageView dept_thumbnail;

    public DepartmentHolder(@NonNull View itemView) {
        super(itemView);
        dept_title = itemView.findViewById(R.id.dept_name);
        dept_thumbnail = itemView.findViewById(R.id.img_id);
    }

    public void setTextView(String text){
        dept_title.setText(text);
    }

    @SuppressLint("ResourceType")
    public void setThumbnail(@IdRes int imageId){
        dept_thumbnail.setImageResource(imageId);
    }
}
