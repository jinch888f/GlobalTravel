package com.dudu.globaltravel.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudu.globaltravel.R;
import com.dudu.globaltravel.TravelListRecyclerViewAdapter;
import com.dudu.globaltravel.TravelSubjectRecyclerViewAdapter;
import com.dudu.globaltravel.bean.AdInfoModel;
import com.dudu.globaltravel.bean.TravelNoteListModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

public class TravelNoteListFragment extends Fragment {
    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private TravelListRecyclerViewAdapter mAdapter;

    private List<AdInfoModel> AdDataList;
    private List<TravelNoteListModel> listData;
    private List<TravelNoteListModel> tempData;

    private OkHttpClient mClient;
    private Gson mGson;
    private int pageCount;
    private static final String baseUrl = "http://chanyouji.com/api/trips/featured.json?page=";

    private static final int MSG_DATAGETSUCCESS = 0;
    private static final int MSG_DATAGETFAILURE = 1;
    private static final int MSG_UPDATESUCCESS = 2;


    /**
     * 创建一个Handler，在获取网络数据和刷新网络数据之后来更新UI
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {//此方法在ui线程运行
            switch (msg.what) {
                case MSG_DATAGETSUCCESS:
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
                    mRecyclerView.setAdapter(mAdapter);
                    mRefreshLayout.finishRefreshing();
                    break;
                case MSG_UPDATESUCCESS:
                    mAdapter.notifyDataSetChanged();
                    break;
                case MSG_DATAGETFAILURE:
                    break;

            }
        }
    };

    /**
     *Fragment必须重写的方法，用来加载对应的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInsatanceState) {
        super.onCreateView(inflater,parent,savedInsatanceState);
        mRefreshLayout = (TwinklingRefreshLayout) inflater.inflate(R.layout.fg_travellist,parent,false);
        mRecyclerView = (RecyclerView)mRefreshLayout.findViewById(R.id.travel_list_recycler);
        AdDataList = new ArrayList<>();
        listData = new ArrayList<>();
        mClient = new OkHttpClient();
        mGson = new Gson();
        pageCount = 1;
        getAdData();
        getListData(pageCount);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                pageCount = 1;
                getListData(pageCount);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageCount++;
                        updateData(pageCount);
                        getAdData();
                    }
                },2000);
            }
        });
        return mRefreshLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 从网络上获取广告轮播数据
     */
    public void getAdData() {
        Request request = new Request.Builder().url("http://chanyouji.com/api/adverts.json?name=app_featured_banner_ios").build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.obtainMessage(MSG_DATAGETFAILURE).sendToTarget();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<List<AdInfoModel>>(){}.getType();
                AdDataList = mGson.fromJson(response.body().string(),type);
            }
        });
    }

    /**
     * 获取列表也得数据
     * @param pageCount 获取数据的参数
     */
    public void getListData(int pageCount) {
        Request request = new Request.Builder().url(baseUrl+pageCount).build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.obtainMessage(MSG_DATAGETFAILURE).sendToTarget();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<List<TravelNoteListModel>>(){}.getType();
                tempData = mGson.fromJson(response.body().string(),type);
                listData.addAll(tempData);
                tempData.clear();
                mAdapter = new TravelListRecyclerViewAdapter(getActivity(), (ArrayList<AdInfoModel>) AdDataList,(ArrayList<TravelNoteListModel>) listData);
                mHandler.obtainMessage(MSG_DATAGETSUCCESS).sendToTarget();
            }
        });
    }

    /**
     * 刷新列表页的数据
     * @param pageCount
     */
    private void updateData(int pageCount) {
        Request request = new Request.Builder().url(baseUrl+pageCount).build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.obtainMessage(MSG_DATAGETFAILURE).sendToTarget();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<List<TravelNoteListModel>>(){}.getType();
                tempData = mGson.fromJson(response.body().string(),type);
                listData.addAll(tempData);
                tempData.clear();
                mHandler.obtainMessage(MSG_UPDATESUCCESS).sendToTarget();
            }
        });
        mRefreshLayout.finishLoadmore();
    }
}
