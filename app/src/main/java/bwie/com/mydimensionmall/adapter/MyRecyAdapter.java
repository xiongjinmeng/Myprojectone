package bwie.com.mydimensionmall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bwie.com.mydimensionmall.R;
import bwie.com.mydimensionmall.boods.BannerBoods;
import bwie.com.mydimensionmall.boods.RecyclerBoods;
import bwie.com.mydimensionmall.fragment.HomepageFragment;
import bwie.com.mydimensionmall.uile.OkHttpUile;
import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.Request;

public class MyRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final RecyclerBoods.ResultBean list;
    private String path = "http://172.17.8.100/small/commodity/v1/bannerShow";

    public MyRecyAdapter(Context context, RecyclerBoods.ResultBean bean) {
        super();
        this.context=context;
        this.list=bean;
    }

    //多条目
    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        } else if (position==1){
            return 1;
        } else if (position==2){
            return 2;
        } else {
            return 3;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View view = View.inflate(context, R.layout.layout_bgabanner, null);
            return new Hodler5(view);
        } else if (i==1){
            View view = View.inflate(context, R.layout.layout_recycler_list_text, null);
            return new Hodler(view);
        } else if (i==2){
            View view = View.inflate(context, R.layout.layout_recycler_list_text, null);
            return new Hodler2(view);
        } else {
            View view = View.inflate(context, R.layout.layout_recycler_list_text, null);
            return new Hodler3(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof Hodler5){
            final Hodler5 hodler5 = (Hodler5) viewHolder;
            //轮播
            OkHttpUile.getInstance().asyncGet(path, new OkHttpUile.HttpCallBack() {

                @Override
                public void onError(Request request, IOException e) {
                    //失败
                }

                @Override
                public void onSuccess(Request request, String result) {
                    Gson gson = new Gson();
                    BannerBoods bannerBoods = gson.fromJson(result, BannerBoods.class);
                    final List<BannerBoods.ResultBean> listest = bannerBoods.getResult();
                    final ArrayList<String> strings = new ArrayList<>();
                    ArrayList<String> textstring = new ArrayList<>();
                    for (int j = 0; j < listest.size(); j++) {
                        String imageUrl = listest.get(j).getImageUrl();
                        String title = listest.get(j).getTitle();
                        strings.add(imageUrl);
                        textstring.add(title);
                    }
                    hodler5.bgabanner.setData(strings,textstring);
                    hodler5.bgabanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                        @Override
                        public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
                            Glide.with(context).load(listest.get(position).getImageUrl()).into(itemView);
                        }
                    });
                }
            });
        } else if (viewHolder instanceof Hodler){
            Hodler hodler = (Hodler) viewHolder;
            for (int j = 0; j < list.getPzsh().size(); j++) {
                //热销新品
                String name = list.getRxxp().get(j).getName();
                List<RecyclerBoods.ResultBean.RxxpBean.CommodityListBean> listrxxp = list.getRxxp().get(j).getCommodityList();
                hodler.text_recycler_rxxp.setText(name);
                hodler.text_recycler_rxxp.setTextColor(Color.parseColor("#FF7F57"));
                hodler.image_recycler_backg.setImageResource(R.drawable.bitmap);
                hodler.image_recycler.setImageResource(R.drawable.common_btn_more_yellow_n_hdpi);
                hodler.image_recycler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HomepageFragment.view_pager_home.setCurrentItem(2);
                    }
                });
                MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(context,listrxxp);
                hodler.grid_view.setAdapter(recyclerAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                linearLayoutManager.setReverseLayout(false);
                hodler.grid_view.setLayoutManager(linearLayoutManager);
            }
        } else if (viewHolder instanceof Hodler2){
            Hodler2 hodler2 = (Hodler2) viewHolder;
            for (int j = 0; j < list.getMlss().size(); j++) {
                //魔力时尚
                String name2 = list.getMlss().get(j).getName();
                List<RecyclerBoods.ResultBean.MlssBean.CommodityListBeanXX> listmlss = list.getMlss().get(j).getCommodityList();
                hodler2.text_recycler_rxxp.setText(name2);
                hodler2.text_recycler_rxxp.setTextColor(Color.parseColor("#7D7FF6"));
                hodler2.image_recycler_backg.setImageResource(R.drawable.bitmap3);
                hodler2.image_recycler.setImageResource(R.drawable.home_btn_more_purple_n_hdpi);
                hodler2.image_recycler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HomepageFragment.view_pager_home.setCurrentItem(2);
                    }
                });
                RecyclermlssAdapter recyclermlssAdapter = new RecyclermlssAdapter(context,listmlss);
                hodler2.grid_view.setAdapter(recyclermlssAdapter);
                LinearLayoutManager linearLayout = new LinearLayoutManager(context);
                linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
                linearLayout.setReverseLayout(false);
                hodler2.grid_view.setLayoutManager(linearLayout);

            }
        } else {
            Hodler3 hodler3 = (Hodler3) viewHolder;
        for (int j = 0; j < list.getPzsh().size(); j++) {
            //品质生活
            String name1 = list.getPzsh().get(j).getName();
            List<RecyclerBoods.ResultBean.PzshBean.CommodityListBeanX> listpzsh = list.getPzsh().get(j).getCommodityList();
            hodler3.text_recycler_rxxp.setText(name1);
            hodler3.text_recycler_rxxp.setTextColor(Color.parseColor("#FF5F71"));
            hodler3.image_recycler_backg.setImageResource(R.drawable.bitmap2);
            hodler3.image_recycler.setImageResource(R.drawable.home_btn_moer_pink_n_hdpi);
            hodler3.image_recycler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HomepageFragment.view_pager_home.setCurrentItem(2);
                }
            });
            GridlistAdapter gridlistAdapter = new GridlistAdapter(context,listpzsh);
            hodler3.grid_view.setAdapter(gridlistAdapter);
                GridLayoutManager linearLa = new GridLayoutManager(context,2);
                linearLa.setOrientation(LinearLayoutManager.VERTICAL);
                linearLa.setReverseLayout(false);
                hodler3.grid_view.setLayoutManager(linearLa);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private class Hodler extends RecyclerView.ViewHolder {
        private final TextView text_recycler_rxxp;
        private final RecyclerView grid_view;
        private final ImageView image_recycler;
        private final ImageView image_recycler_backg;
        public Hodler(@NonNull View itemView) {
            super(itemView);
            text_recycler_rxxp = itemView.findViewById(R.id.text_recycler_rxxp);
            grid_view = itemView.findViewById(R.id.grid_view);
            image_recycler = itemView.findViewById(R.id.image_recycler);
            image_recycler_backg = itemView.findViewById(R.id.image_recycler_backg);
        }
    }

    private class Hodler2 extends RecyclerView.ViewHolder {
        private final TextView text_recycler_rxxp;
        private final RecyclerView grid_view;
        private final ImageView image_recycler;
        private final ImageView image_recycler_backg;
        public Hodler2(View view) {
            super(view);
            text_recycler_rxxp = itemView.findViewById(R.id.text_recycler_rxxp);
            grid_view = itemView.findViewById(R.id.grid_view);
            image_recycler = itemView.findViewById(R.id.image_recycler);
            image_recycler_backg = itemView.findViewById(R.id.image_recycler_backg);
        }
    }

    private class Hodler3 extends RecyclerView.ViewHolder {
        private final TextView text_recycler_rxxp;
        private final RecyclerView grid_view;
        private final ImageView image_recycler;
        private final ImageView image_recycler_backg;
        public Hodler3(View view) {
            super(view);
            text_recycler_rxxp = itemView.findViewById(R.id.text_recycler_rxxp);
            grid_view = itemView.findViewById(R.id.grid_view);
            image_recycler = itemView.findViewById(R.id.image_recycler);
            image_recycler_backg = itemView.findViewById(R.id.image_recycler_backg);
        }
    }

    private class Hodler5 extends RecyclerView.ViewHolder {
        private final BGABanner bgabanner;

        public Hodler5(View view) {
            super(view);
            bgabanner = view.findViewById(R.id.bgabanner);
        }
    }
}
