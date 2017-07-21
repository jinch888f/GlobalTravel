package com.dudu.globaltravel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dudu.globaltravel.R;
import com.dudu.globaltravel.TravelListRecyclerViewAdapter;

/**
 * Created by mac on 2017/7/21.
 */

public class NoteListDetailAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    private Context mContext;
    public static enum ITEM_TYPE {
        ITEM_TYPE_TEXT,
        ITEM_TYPE_IMAGE
    }

    public NoteListDetailAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.note_list_detail_image_item,viewGroup,false);
            return new NoteListDetailImageViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.note_list_detail_text_item,viewGroup,false);
            return new NoteListDetailViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof NoteListDetailViewHolder) {

        }else {
            View view = ((NoteListDetailImageViewHolder)viewHolder).mView;
            ImageView imageView = (ImageView)view.findViewById(R.id.show_detail_image);
            imageView.setImageResource(R.mipmap.haidao);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2 == 0)? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal():ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    public static class NoteListDetailViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public NoteListDetailViewHolder(View view) {
            super(view);
            mView = view;
        }
    }

    public static class NoteListDetailImageViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public NoteListDetailImageViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
