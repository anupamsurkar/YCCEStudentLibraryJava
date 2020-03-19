package com.syrous.yccestudentlibraryjava.ui.pager;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelPaper;

import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.MyViewHolder> {

    private Context context;
    private List<ModelPaper> mData;
    private Activity activity;

    public PagerAdapter(Activity activity,Context context, List<ModelPaper> mData) {
        this.context = context;
        this.mData = mData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subject_card_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = mData.get(position).getExamType() + " - " + mData.get(position).getExaminationYear();
        holder.paperName.setText(name);
        holder.courseCode.setText(mData.get(position).getUploadedBy());
        holder.mainLayout.setOnClickListener((View) -> {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(-65536);
            builder.setShowTitle(true);
            CustomTabsIntent customTabsIntent = builder.build();
            String url = mData.get(position).getDownloadUrl();
            try {
                customTabsIntent.launchUrl(activity, Uri.parse(url));
            }catch (ActivityNotFoundException e){
                Log.d("Activity", "ActivityNotFound : "+e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView paperName;
        private TextView courseCode;
        ConstraintLayout mainLayout;
        public MyViewHolder(View itemView){
            super(itemView);
            paperName = itemView.findViewById(R.id.subname);
            courseCode = itemView.findViewById(R.id.coursecode);
            mainLayout = itemView.findViewById(R.id.card_on);
        }
    }
}
