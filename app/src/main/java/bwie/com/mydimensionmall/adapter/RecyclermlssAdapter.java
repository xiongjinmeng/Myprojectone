package bwie.com.mydimensionmall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.mydimensionmall.R;
import bwie.com.mydimensionmall.boods.RecyclerBoods;

class RecyclermlssAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<RecyclerBoods.ResultBean.MlssBean.CommodityListBeanXX> list;

    public RecyclermlssAdapter(Context context, List<RecyclerBoods.ResultBean.MlssBean.CommodityListBeanXX> listmlss) {
        super();
        this.context=context;
        this.list=listmlss;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.recycler_layout, null);
        return new HolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HolderView holderView = (HolderView) viewHolder;
        String name = list.get(i).getCommodityName();
        double price = list.get(i).getPrice();
        String pic = list.get(i).getMasterPic();
        holderView.text_grid_name.setText(name);
        holderView.text_grid_pras.setText("￥"+price);
        Glide.with(context).load(pic).into(holderView.image_grid);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class HolderView extends RecyclerView.ViewHolder {
        private final ImageView image_grid;
        private final TextView text_grid_pras;
        private final TextView text_grid_name;
        public HolderView(View view) {
            super(view);
            image_grid = itemView.findViewById(R.id.image_grid2);
            text_grid_name = itemView.findViewById(R.id.text_grid_name2);
            text_grid_pras = itemView.findViewById(R.id.text_grid_pras2);
        }
    }
}
