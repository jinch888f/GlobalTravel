package com.dudu.globaltravel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudu.globaltravel.R;
import com.dudu.globaltravel.bean.TravelTipsListModel;

import java.util.List;

/**
 * Created by mac on 2017/7/13.
 */

public class TravelTipsInHomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    private WindowManager mWindowManager;
    private int screenWidth;
    private List<TravelTipsListModel> mModels;
    public TravelTipsInHomeListAdapter(Context context, List<TravelTipsListModel> models) {
        this.mContext = context;
        this.mModels = models;
        System.out.println("--------"+models.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.travel_tipslist_item,viewGroup,false);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = mWindowManager.getDefaultDisplay().getWidth();
        view.getLayoutParams().height = ((((screenWidth -20)/2)*4)/3);
        return new TravelTipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = ((TravelTipsViewHolder)holder).mView;
        ImageView mainImage = (ImageView) view.findViewById(R.id.tips_imageView);
        TextView titleView = (TextView) view.findViewById(R.id.tips_title);
        TextView infoView = (TextView) view.findViewById(R.id.tips_info);
        Glide.with(mContext).load(mModels.get(position).image_url).into(mainImage);
        titleView.setText(mModels.get(position).name_zh_cn+"\n"+mModels.get(position).name_en);
        infoView.setText("旅行地"+mModels.get(position).poi_count);

    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public static class TravelTipsViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        public TravelTipsViewHolder (View view) {
            super(view);
            mView = view;
        }
    }
}
