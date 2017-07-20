package com.dudu.globaltravel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dudu.globaltravel.bean.AdInfoModel;
import com.dudu.globaltravel.bean.TravelNoteListModel;
import com.dudu.globaltravel.ui.activity.TravelNoteDetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mac on 2017/6/20.
 */

public class TravelListRecyclerViewAdapter extends RecyclerView.Adapter
        <RecyclerView.ViewHolder> {
    private List<AdInfoModel> AdDataList;
    private List<TravelNoteListModel> listData;
    private WindowManager mWindowManager;
    private int screenWidth;


    public static enum ITME_TYPE {
        ITME_TYPE_NORMAL,
        ITME_TYPE_HEADER
    }
    private Context mContext;
    public TravelListRecyclerViewAdapter(Context context, ArrayList<AdInfoModel> list,ArrayList<TravelNoteListModel> listData) {
        this.mContext = context;
        this.AdDataList = list;
        this.listData = listData;

     }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITME_TYPE.ITME_TYPE_NORMAL.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_list_item,parent, false);
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            screenWidth = mWindowManager.getDefaultDisplay().getWidth();
            view.getLayoutParams().height = screenWidth/2;
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_list_header_tiem,parent,false);
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            screenWidth = mWindowManager.getDefaultDisplay().getWidth();
            view.getLayoutParams().height = screenWidth/2;
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final View view = ((ViewHolder)holder).mView;
            final ImageView imageView = (ImageView) view.findViewById(R.id.main_imageView);
            final ImageView user_icon = (ImageView) view.findViewById(R.id.user_icon);
            final TextView title = (TextView) view.findViewById(R.id.m_title);
            final TextView sub_title = (TextView) view.findViewById(R.id.sub_title);
            Glide.with(mContext).load(listData.get(position-1).front_cover_photo_url).into(imageView);
            Glide.with(mContext).load(listData.get(position-1).user.image).into(user_icon);
            title.setText(listData.get(position-1).name);
            sub_title.setText(listData.get(position - 1).start_date+"/"+listData.get(position - 1).days+"天/"+listData.get(position - 1).photos_count+"图");

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TravelNoteDetailActivity.class);
                    mContext.startActivity(intent);
                    showToast("点击了第" + position + "行");
                }
            });
        }else {
            final View view = ((HeaderViewHolder)holder).mView;
            final BGABanner banner = (BGABanner)view.findViewById(R.id.bga_banner);
            banner.setAdapter(new BGABanner.Adapter() {
                @Override
                public void fillBannerItem(BGABanner banner, View itemView, Object model, int position) {
                    Glide.with(mContext).load(model).into((ImageView) itemView);

                }
            });
            banner.setData(
                    Arrays.asList(
                            AdDataList.get(0).getImage_url()
                           ,AdDataList.get(1).getImage_url()
                           ,AdDataList.get(2).getImage_url()
                           ,AdDataList.get(3).getImage_url()
                           ,AdDataList.get(4).getImage_url()
                           ,AdDataList.get(5).getImage_url())
                    ,Arrays.asList("订票上携程","蝉游记新版发布","我要上首页","快到碗里来","1分钟写游记","3分钟做攻略"));
            banner.setDelegate(new BGABanner.Delegate() {
                @Override
                public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                    Uri uri = Uri.parse("http://www.taobao.com");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    mContext.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ViewHolder (View view) {
            super(view);
            mView = view;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position > 0?ITME_TYPE.ITME_TYPE_NORMAL.ordinal():ITME_TYPE.ITME_TYPE_HEADER.ordinal();
    }



    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public HeaderViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
    private void showToast(String string) {
        Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show();
    }

}
