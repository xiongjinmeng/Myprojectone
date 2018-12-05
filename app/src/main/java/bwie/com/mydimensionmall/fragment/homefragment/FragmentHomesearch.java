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
public class FragmentHomesearch extends Fragment {


    public FragmentHomesearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_homesearch, container, false);
        return view;
    }

}
