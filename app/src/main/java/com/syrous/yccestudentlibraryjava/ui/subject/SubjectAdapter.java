package com.syrous.yccestudentlibraryjava.ui.subject;

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
import com.syrous.yccestudentlibraryjava.data.ModelSubject;
import com.syrous.yccestudentlibraryjava.ui.pager.ActivityPager;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubViewHolder>{

    private Context context;
    private List<ModelSubject> subjects;

    public SubjectAdapter(Context context, List<ModelSubject> subjects){
            this.context = context;
            this.subjects = subjects;
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subject_card_layout, parent, false);
        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
        holder.subjectName.setText(subjects.get(position).getSubjectName());
        holder.courseCode.setText(subjects.get(position).getCourseCode());
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent= new Intent(context, ActivityPager.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class SubViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout mainLayout;
        private TextView subjectName;
        private TextView courseCode;

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.subname);
            courseCode = itemView.findViewById(R.id.coursecode);
            mainLayout = itemView.findViewById(R.id.cardcon);
        }
    }

}
