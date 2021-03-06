package com.syrous.yccestudentlibraryjava.ui.paper_pager;


import android.content.Context;
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
import com.syrous.yccestudentlibraryjava.data.ModelResource;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyMaterialFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private List<ModelResource> resourceList;
    private FirebaseFirestore db;
    private String path;
    private Context context;

    public StudyMaterialFragment(Context context) {
        this.context = context;
    }

    public static StudyMaterialFragment newInstance(Context context, String path, String exam){
        StudyMaterialFragment fragment = new StudyMaterialFragment(context);
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
      View v = inflater.inflate(R.layout.fragment_pager,container,false);

        mRecyclerview = v.findViewById(R.id.recycler_pager);

        db.collection(path)
                .get()
                .addOnSuccessListener((task) -> {
                    resourceList = new ArrayList<>();
                    for(DocumentSnapshot doc: task){
//                        ModelResource resource = new ModelResource();
//                        resourceList.add(resource);
                    }

                    StudyMaterialAdapter adapter = new StudyMaterialAdapter(getActivity(), resourceList);
                    mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerview.setAdapter(adapter);

                });

        return v;
    }
}
