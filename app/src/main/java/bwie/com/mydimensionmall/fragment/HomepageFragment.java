package bwie.com.mydimensionmall.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bwie.com.mydimensionmall.R;
import bwie.com.mydimensionmall.adapter.MyFragmentHomeAdapter;
import bwie.com.mydimensionmall.fragment.homefragment.FragmentHomedetails;
import bwie.com.mydimensionmall.fragment.homefragment.FragmentHomegoods;
import bwie.com.mydimensionmall.fragment.homefragment.FragmentHomesearch;
import bwie.com.mydimensionmall.uile.CustomViewPager;


public class HomepageFragment extends Fragment {


    private View view;
    public static CustomViewPager view_pager_home;
    private ArrayList<Fragment> fragment;
    private FragmentHomegoods homegoods;
    private FragmentHomedetails homedetails;
    private FragmentHomesearch homesearch;
    private MyFragmentHomeAdapter homeAdapter;

    public HomepageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, container, false);

        initLayout();
        initData();
        initListening();
        return view;
    }

    private void initLayout() {
        view_pager_home = view.findViewById(R.id.view_pager_home);
        view_pager_home.setOffscreenPageLimit(2);
    }

    private void initData() {
        fragment = new ArrayList<>();
        homedetails = new FragmentHomedetails();
        homegoods = new FragmentHomegoods();
        homesearch = new FragmentHomesearch();
        fragment.add(homedetails);
        fragment.add(homegoods);
        fragment.add(homesearch);
        homeAdapter = new MyFragmentHomeAdapter(getFragmentManager());
        homeAdapter.setList(fragment);
        view_pager_home.setAdapter(homeAdapter);
        view_pager_home.setCurrentItem(1);
    }

    private void initListening() {
        view_pager_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        view_pager_home.setScanScroll(false);
                        break;
                    case 1:
                        view_pager_home.setScanScroll(false);
                        break;
                    case 2:
                        view_pager_home.setScanScroll(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
