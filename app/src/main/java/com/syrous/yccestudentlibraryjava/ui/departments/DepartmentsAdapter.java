package com.syrous.yccestudentlibraryjava.ui.departments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.civil.CivilHome;
import com.syrous.yccestudentlibraryjava.ui.ctech.CtechHome;
import com.syrous.yccestudentlibraryjava.ui.ee.ElectronicsHome;
import com.syrous.yccestudentlibraryjava.ui.el.ElectricalHome;
import com.syrous.yccestudentlibraryjava.ui.etc.EtcHome;
import com.syrous.yccestudentlibraryjava.ui.it.ItHome;
import com.syrous.yccestudentlibraryjava.ui.mech.MechHome;

import java.util.List;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentHolder> {


    private Context mContext;
    private List<Departments> mData;

    public DepartmentsAdapter(Context mContext, List<Departments> mData) {
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
                Intent intent = new Intent(mContext, CivilHome.class);
                mContext.startActivity(intent);
            }
            if(position == 1){
                Intent intent = new Intent(mContext, CtechHome.class);
                mContext.startActivity(intent);
            }
            if(position == 2){
                Intent intent = new Intent(mContext, CivilHome.class);
                mContext.startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(mContext, ElectricalHome.class);
                mContext.startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(mContext, EtcHome.class);
                mContext.startActivity(intent);
            }
            if(position == 5){
                Intent intent = new Intent(mContext, ElectronicsHome.class);
                mContext.startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(mContext, MechHome.class);
                mContext.startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(mContext, ItHome.class);
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

    public void setThumbnail(@IdRes int imageId){
        dept_thumbnail.setImageResource(imageId);
    }
}
