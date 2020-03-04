package com.syrous.yccestudentlibraryjava.ui.contact_us;

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
 * date : 13/2/20
 */
public class FragmentContactUs extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_us, container, false);

    }
}
