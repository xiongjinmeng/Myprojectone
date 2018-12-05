package bwie.com.mydimensionmall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentHomeAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;

    public MyFragmentHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(ArrayList<Fragment> fragment) {
        this.list=fragment;
    }
}
