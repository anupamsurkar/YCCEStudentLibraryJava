package com.syrous.yccestudentlibraryjava.ui.paper_pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelResource;

import java.util.List;

public class StudyMaterialAdapter extends RecyclerView.Adapter<StudyMaterialAdapter.StudyMaterialViewHolder>{


    private List<ModelResource> resourceList;
    private Context context;
    public StudyMaterialAdapter(Context context, List<ModelResource> resourceList){
        this.context = context;
        this.resourceList = resourceList;
    }

    @NonNull
    @Override
    public StudyMaterialAdapter.StudyMaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subject_card_layout, parent, false);
        return new StudyMaterialViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudyMaterialViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class StudyMaterialViewHolder extends RecyclerView.ViewHolder{

        public StudyMaterialViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
