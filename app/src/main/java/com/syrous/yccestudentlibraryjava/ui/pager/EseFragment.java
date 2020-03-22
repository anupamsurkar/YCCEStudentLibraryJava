package com.syrous.yccestudentlibraryjava.ui.pager;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;
import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.data.ModelPaper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EseFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private List<ModelPaper> esePaperList;
    private FirebaseFirestore db;
    private String path;
    private Activity activity;

    public EseFragment(Activity activity) {
        this.activity = activity;
    }

    public static EseFragment newInstance(Activity activity, String path, String exam){
        EseFragment fragment = new EseFragment(activity);
        Bundle args = new Bundle();
        args.putString(GlobalConstants.EXAM_NAME, exam);
        args.putString(GlobalConstants.DOWNLOAD_SERVER_PATH, path);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        assert getArguments() != null;
        path = getArguments().getString(GlobalConstants.DOWNLOAD_SERVER_PATH) +
                "/" + getArguments().getString(GlobalConstants.EXAM_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager, container, false);
        mRecyclerview = v.findViewById(R.id.recycler_pager);

        db.collection(path)
                .get()
                .addOnSuccessListener((task) -> {
                    esePaperList = new ArrayList<>();
                   for(DocumentSnapshot doc: task.getDocuments()){
                      ModelPaper paper = new ModelPaper(doc.getId(), doc.get(GlobalConstants.DEPARTMENT_NAME).toString(),
                              doc.get(GlobalConstants.COURSE_CODE).toString(), doc.get(GlobalConstants.EXAM).toString(),
                              doc.get(GlobalConstants.NAME_FIELD).toString(), doc.get(GlobalConstants.TIME_FIELD).toString(),
                              doc.get(GlobalConstants.URL_FIELD).toString(), doc.get(GlobalConstants.TIME_FIELD).toString(),
                              Integer.parseInt(doc.get(GlobalConstants.EXAMINATION_YEAR).toString()));
                       esePaperList.add(paper);
                   }
                    PagerAdapter recyclerViewAdapter = new PagerAdapter(activity, getActivity(), esePaperList);
                    mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerview.setAdapter(recyclerViewAdapter);
                });


        return v;
    }
}
