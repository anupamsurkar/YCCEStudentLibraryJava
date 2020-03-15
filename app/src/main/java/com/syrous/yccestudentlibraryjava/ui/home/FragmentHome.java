package com.syrous.yccestudentlibraryjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.departments.DepartmentsAdapter;
import com.syrous.yccestudentlibraryjava.ui.departments.ModelDepartments;
import com.syrous.yccestudentlibraryjava.ui.other_features.ModelOF;
import com.syrous.yccestudentlibraryjava.ui.other_features.OFAdapter;
import com.syrous.yccestudentlibraryjava.ui.recents.ModelRecents;
import com.syrous.yccestudentlibraryjava.ui.recents.RecentsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By : Syrous
 * date : 6/2/20
 */

public class FragmentHome extends Fragment {

    private List<ModelDepartments> oneDept;
    private List<ModelOF> modelOF;
    private List<ModelRecents> modelRecents;
    private RecentsAdapter recentsAdapter;
    private OFAdapter ofAdapter;
    private RecyclerView RecentRecycler;
    private RecyclerView OfRecycler;

   Button upload;



    public static FragmentHome newInstance(){
        return new FragmentHome();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        oneDept= new ArrayList<>();
        oneDept.add(new ModelDepartments("First Year", R.drawable.baby, "fy"));
        oneDept.add(new ModelDepartments("C.Tech", R.drawable.api, "ct"));
        oneDept.add(new ModelDepartments("Civil", R.drawable.crane, "cv"));
        oneDept.add(new ModelDepartments("Electrical", R.drawable.electricmotor, "el"));
        oneDept.add(new ModelDepartments("ETC", R.drawable.tower, "et"));
        oneDept.add(new ModelDepartments("Electronics", R.drawable.cpu, "ee"));
        oneDept.add(new ModelDepartments("Mechanical", R.drawable.work, "me"));
        oneDept.add(new ModelDepartments("IT", R.drawable.tv, "it"));

        RecyclerView mrv = v.findViewById(R.id.home_recycler);

        DepartmentsAdapter adapter = new DepartmentsAdapter(getActivity(), oneDept);
        mrv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mrv.setAdapter(adapter);

        OfRecycler = v.findViewById(R.id.recycler_other_features);

        String[] feature_name = { "YCCE", "Moodle", "Upload Paper", "Online Fee Payment","More"};
        Integer[] feature_logos = {R.drawable.home, R.drawable.moodle,
                R.drawable.upload, R.drawable.pay, R.drawable.more1};

        modelOF = new ArrayList<>();

        for( int i= 0; i < feature_logos.length; i++){
            ModelOF oFeatures = new ModelOF(feature_logos[i], feature_name[i]);
            modelOF.add(oFeatures);
        }

        LinearLayoutManager oLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        OfRecycler.setLayoutManager(oLayoutManager);
        OfRecycler.setItemAnimator(new DefaultItemAnimator());

        ofAdapter = new OFAdapter(getActivity(), modelOF);
        OfRecycler.setAdapter(ofAdapter);



        RecentsAdapter recentsAdapter ;
//


        String recent = "Recent";

        Integer[] feature_logos1 = {R.drawable.recent};

        RecentRecycler = v.findViewById(R.id.recents_recycler);
        modelRecents = new ArrayList<>();

        for( int i= 0; i < feature_logos.length; i++){
            ModelRecents oFeatures = new ModelRecents(feature_logos1[0], recent);
            modelRecents.add(oFeatures);
        }

        LinearLayoutManager oLayoutManager1= new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        RecentRecycler.setLayoutManager(oLayoutManager1);
        RecentRecycler.setItemAnimator(new DefaultItemAnimator());

        recentsAdapter = new RecentsAdapter(getActivity(), modelRecents);
        RecentRecycler.setAdapter(recentsAdapter);
        return v;


    }
}
