package bwie.com.mydimensionmall.fragment.homefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bwie.com.mydimensionmall.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHomedetails extends Fragment {


    public FragmentHomedetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_homedetails, container, false);
    }

}
