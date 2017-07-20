package com.dudu.globaltravel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudu.globaltravel.bean.TravelSubjectListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/6/21.
 */

public class TravelSubjectRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private WindowManager mWindowManager;
    private int screenWidth;
    private Context mContext;
    private List<TravelSubjectListModel> mListData;

    public TravelSubjectRecyclerViewAdapter(Context context, ArrayList<TravelSubjectListModel> list) {
        this.mContext = context;
        this.mListData = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.travelnote_sunject_list_item,viewGroup,false);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = mWindowManager.getDefaultDisplay().getWidth();
        view.getLayoutParams().height = screenWidth/2;
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        View view = ((SubjectViewHolder)holder).mView;
        final ImageView imageView = (ImageView)view.findViewById(R.id.travel_subject_imageView);
        final TextView title = (TextView)view.findViewById(R.id.travel_subject_title);
        final TextView subTitle = (TextView)view.findViewById(R.id.travel_subject_subTitle);
        Glide.with(mContext).load(mListData.get(position).image_url).into(imageView);
        title.setText(mListData.get(position).name);
        subTitle.setText(mListData.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }




    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        public SubjectViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
