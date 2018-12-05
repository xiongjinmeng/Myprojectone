package bwie.com.mydimensionmall.fragment.homefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;

import bwie.com.mydimensionmall.R;
import bwie.com.mydimensionmall.adapter.MyRecyAdapter;
import bwie.com.mydimensionmall.boods.RecyclerBoods;
import bwie.com.mydimensionmall.fragment.HomepageFragment;
import bwie.com.mydimensionmall.uile.OkHttpUile;
import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.Request;


public class FragmentHomegoods extends Fragment {


    private View view;
    private ImageView image_choose;
    private ImageView image_search;
    private BGABanner bgabanner;
    private RecyclerView recycler_view;
    private String pathrecy = "http://172.17.8.100/small/commodity/v1/commodityList";

    public FragmentHomegoods() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_homegoods, container, false);
        //初始化
        initLayout();
        initData();
        initListening();
        return view;
    }

    private void initLayout() {
        image_choose = view.findViewById(R.id.image_choose);
        image_search = view.findViewById(R.id.image_search);
        bgabanner = view.findViewById(R.id.bgabanner);
        recycler_view = view.findViewById(R.id.recycler_view);

    }

    private void initData() {

        //首页的数据
        OkHttpUile.getInstance().asyncGet(pathrecy, new OkHttpUile.HttpCallBack() {

            //失败
            @Override
            public void onError(Request request, IOException e) {

            }

            //成功
            @Override
            public void onSuccess(Request request, String result) {
                Gson gson = new Gson();
                RecyclerBoods recyclerBoods = gson.fromJson(result, RecyclerBoods.class);
                RecyclerBoods.ResultBean bean = recyclerBoods.getResult();
                MyRecyAdapter recyAdapter = new MyRecyAdapter(getActivity(),bean);
                recycler_view.setAdapter(recyAdapter);
                //RecyclerView必须有布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                linearLayoutManager.setReverseLayout(false);
                recycler_view.setLayoutManager(linearLayoutManager);

            }
        });
    }

    private void initListening() {
        //搜索
        image_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomepageFragment.view_pager_home.setCurrentItem(2);
            }
        });


    }

}
