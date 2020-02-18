package com.syrous.yccestudentlibraryjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.departments.Departments;
import com.syrous.yccestudentlibraryjava.ui.departments.DepartmentsAdapter;
import com.syrous.yccestudentlibraryjava.ui.other_features.OfAdapter;
import com.syrous.yccestudentlibraryjava.ui.other_features.OtherFeatures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By : Syrous
 * date : 6/2/20
 */
public class HomeFragment extends Fragment {

    private List<Departments> oneDept;
    private List<OtherFeatures> otherFeatures;
    private OfAdapter ofAdapter;
    private RecyclerView OfRecycler;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_scroll, container, false);


        oneDept= new ArrayList<>();
        oneDept.add(new Departments("First Year", R.drawable.baby));
        oneDept.add(new Departments("C.Tech", R.drawable.api));
        oneDept.add(new Departments("Civil", R.drawable.crane));
        oneDept.add(new Departments("Electrical", R.drawable.electricmotor));
        oneDept.add(new Departments("ETC", R.drawable.tower));
        oneDept.add(new Departments("Electronics", R.drawable.cpu));
        oneDept.add(new Departments("Mechanical", R.drawable.work));
        oneDept.add(new Departments("IT", R.drawable.tv));



        RecyclerView mrv= v.findViewById(R.id.home_recycler);

        DepartmentsAdapter myAdapter = new DepartmentsAdapter(getActivity(), oneDept);
        mrv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mrv.setAdapter(myAdapter);

        OfRecycler = v.findViewById(R.id.recycler_other_features);

        Integer[] feature_logos = {R.drawable.baby, R.drawable.api,
                R.drawable.tower, R.drawable.tv,
                R.drawable.crane, R.drawable.work, R.drawable.cpu};

        String[] feature_name = { "Upload Resources", "Attendance", "Online Fee Payment", "ESE Answer sheets", "Exam Dorm Acceptance", "Class Schedule", "Logout"};

        otherFeatures = new ArrayList<>();

        for( int i= 0; i<feature_logos.length; i++){
            OtherFeatures oFeatures = new OtherFeatures(feature_logos[i], feature_name[i]);
            otherFeatures.add(oFeatures);
        }

        LinearLayoutManager oLayoutManager= new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        OfRecycler.setLayoutManager(oLayoutManager);
        OfRecycler.setItemAnimator(new DefaultItemAnimator());

        ofAdapter = new OfAdapter(getActivity(), otherFeatures);
        OfRecycler.setAdapter(ofAdapter);

        return v;
    }
}
