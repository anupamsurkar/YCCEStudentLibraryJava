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

import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelSubject;
import com.syrous.yccestudentlibraryjava.ui.paper_pager.ActivityPager;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubViewHolder> {

    private Context context;
    private List<ModelSubject> subjects;
    private String path;
    private String textView;


    public SubjectAdapter(Context context, List<ModelSubject> subjects, String path, String textView){
            this.context = context;
            this.subjects = subjects;
            this.path = path;
            this.textView = textView;
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
            Intent intent = new Intent(context, ActivityPager.class);
            String subName = String.valueOf(subjects.get(position).getSubjectName());
            intent.putExtra(GlobalConstants.DOWNLOAD_SERVER_PATH, path);
            intent.putExtra(GlobalConstants.SUBJECT_NAME, subName);
            intent.putExtra(GlobalConstants.COURSE_CODE, subjects.get(position).getCourseCode());
            intent.putExtra(GlobalConstants.DEPARTMENT_NAME, textView);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

}

class SubViewHolder extends RecyclerView.ViewHolder{

     ConstraintLayout mainLayout;
     TextView subjectName;
     TextView courseCode;

    public SubViewHolder(@NonNull View itemView) {
        super(itemView);

        subjectName = itemView.findViewById(R.id.subname);
        courseCode = itemView.findViewById(R.id.coursecode);
        mainLayout = itemView.findViewById(R.id.card_on);
    }
}
