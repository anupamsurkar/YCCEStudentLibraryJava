package com.syrous.yccestudentlibraryjava.ui.pager;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syrous.yccestudentlibraryjava.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MseFragment extends Fragment {


    View v;
    private RecyclerView mrecyclerview;
    private List<paper_tabs> list_paper;
    public MseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mse,container,false);
        mrecyclerview = (RecyclerView) v.findViewById(R.id.recycler_mse);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),list_paper);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerview.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_paper = new ArrayList<>();
        list_paper.add(new paper_tabs("mse3 2018-19"));
        list_paper.add(new paper_tabs("mse2 2018-19"));
        list_paper.add(new paper_tabs("mse2 2017-18"));
        list_paper.add(new paper_tabs("mse1 2016-17"));

    }

}
