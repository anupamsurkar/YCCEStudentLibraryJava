package com.syrous.yccestudentlibraryjava.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.pager.ActivityPager;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubViewHolder>{

    String data1[], data2[];
    Context context;
    public SubjectAdapter(Context con, String subject_Name[], String course_Code[]){
            context= con;
            data1= subject_Name;
            data2= course_Code;

    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View  view= inflater.inflate(R.layout.subject_card_layout,parent, false);




        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
        holder.text1.setText(data1[position]);
        holder.text2.setText(data2[position]);
        holder.mainlayout.setOnClickListener(v -> {
            Intent intent= new Intent(context, ActivityPager.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class SubViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout mainlayout;

        TextView text1, text2;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);

            text1= itemView.findViewById(R.id.subname);
            text2= itemView.findViewById(R.id.coursecode);
            mainlayout= itemView.findViewById(R.id.cardcon);


        }
    }

}
