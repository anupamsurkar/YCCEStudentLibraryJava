package com.syrous.yccestudentlibraryjava.ui.departments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.civil.CivilHOme;
import com.syrous.yccestudentlibraryjava.ui.ctech.CtechHome;
import com.syrous.yccestudentlibraryjava.ui.ee.ElectronicsHome;
import com.syrous.yccestudentlibraryjava.ui.el.ElectricalHome;
import com.syrous.yccestudentlibraryjava.ui.etc.EtcHome;
import com.syrous.yccestudentlibraryjava.ui.it.ItHome;
import com.syrous.yccestudentlibraryjava.ui.mech.MechHome;

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
        holder.dept_title.setText(mdata.get(position).getDept());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0){
                    Intent intent = new Intent(mContext, CivilHOme.class);
                    mContext.startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(mContext, CtechHome.class);
                    mContext.startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(mContext, CivilHOme.class);
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
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyVIewHolder extends RecyclerView.ViewHolder{

        TextView dept_title;
        ImageView dept_thumbnail;
        CardView cardView;

        public MyVIewHolder(@NonNull View itemView) {
            super(itemView);

            dept_title= (TextView) itemView.findViewById(R.id.dept_name);
            dept_thumbnail= (ImageView) itemView.findViewById(R.id.img_id);
            cardView = (CardView)itemView.findViewById(R.id.id_card);





        }
    }
}
