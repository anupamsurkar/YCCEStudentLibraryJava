package com.syrous.yccestudentlibraryjava.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.syrous.yccestudentlibraryjava.R;

/**
 * Created By : Syrous
 * date : 6/2/20
 */
public class HomeFragment extends Fragment {

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);




        return v;

    }
}
