package com.syrous.yccestudentlibraryjava.ui.pager;


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
public class MseFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private FirebaseFirestore db;
    private List<ModelPaper> msePaperList;
    private String path;

    public MseFragment() {
        // Required empty public constructor
    }

    public static MseFragment newInstance(String path, String exam){
        MseFragment fragment = new MseFragment();
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
        path = getArguments().getString(GlobalConstants.DOWNLOAD_SERVER_PATH) +
                "/" + getArguments().getString(GlobalConstants.EXAM_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mse,container,false);
        mRecyclerview = v.findViewById(R.id.recycler_mse);
        db.collection(path)
                .get()
                .addOnSuccessListener((task) -> {
                    msePaperList = new ArrayList<>();
                   for(DocumentSnapshot doc: task){
                       ModelPaper paper = new ModelPaper(doc.getId(), doc.get(GlobalConstants.DEPARTMENT_NAME).toString(),
                               doc.get(GlobalConstants.COURSE_CODE).toString(), doc.get(GlobalConstants.EXAM).toString(),
                               doc.get(GlobalConstants.NAME_FIELD).toString(), doc.get(GlobalConstants.TIME_FIELD).toString(),
                               doc.get(GlobalConstants.TIME_FIELD).toString(), doc.get(GlobalConstants.URL_FIELD).toString(),
                               Integer.parseInt(doc.get(GlobalConstants.EXAMINATION_YEAR).toString()));
                        msePaperList.add(paper);
                   }
                    PagerAdapter adapter = new PagerAdapter(getContext(),msePaperList);
                    mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerview.setAdapter(adapter);
                });

        return v;
    }

}
