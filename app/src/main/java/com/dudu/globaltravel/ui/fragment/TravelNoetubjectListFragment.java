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
import com.dudu.globaltravel.bean.TravelSubjectListModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mac on 2017/6/21.
 */

public class TravelNoetubjectListFragment extends Fragment {
    //视图控件
    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    //数据
    private List<TravelSubjectListModel> tempData;
    private List<TravelSubjectListModel> mListData;
    private TravelSubjectRecyclerViewAdapter mAdapter;

    private static final String baseUrl = "http://chanyouji.com/api/articles.json?page=";
    private static final int MSG_DATAGETSUCCESS = 0;
    private static final int MSG_DATAGETFAILURE = 1;
    private static final int MSG_UPDATESUCCESS = 2;

    private OkHttpClient mClient;
    private Gson mGson;

    private int pageCount;

    /**
     * 网络请求到数据或者数据变化时处理界面变化
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {//此方法在ui线程运行
            switch (msg.what) {
                case MSG_DATAGETSUCCESS:
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
                    mRecyclerView.setAdapter(mAdapter);
                    break;
                case MSG_UPDATESUCCESS:
                    //RecyclerView的数据发生变化的时候要调用一下notifyDataSetChanged
                    mAdapter.notifyDataSetChanged();
                    break;
                case MSG_DATAGETFAILURE:
                    break;
            }
        }
    };

    /**
     *Fragment加载对应的视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup parent, Bundle savedInsatanceState) {

        mRefreshLayout = (TwinklingRefreshLayout) inflater.inflate(R.layout.fg_travelnotesubjectlist,parent,false);
        mRecyclerView = (RecyclerView)mRefreshLayout.findViewById(R.id.travel_subject_recyclerView);
        mClient = new OkHttpClient();
        mGson = new Gson();
        pageCount = 1;
        mListData = new ArrayList<>();
        getListData(pageCount);
        //设置上拉加载更多和下拉刷新
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                pageCount = 1;
                mListData.clear();
                getListData(pageCount);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        pageCount++;
                        updateData(pageCount);
                    }
                },2000);
            }
        });
        return mRefreshLayout;
    }

    /**
     *Fragment所依附的Activity创建的时候调用此方法
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 获取网络数据
     * @param pageCount 数据分页请求的参数
     */
    public void getListData(int pageCount){
        Request request = new Request.Builder().url(baseUrl+pageCount).build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.obtainMessage(MSG_DATAGETFAILURE).sendToTarget();
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response);
                Type type = new TypeToken<List<TravelSubjectListModel>>(){}.getType();
                tempData = mGson.fromJson(response.body().string(),type);
                mListData.addAll(tempData);
                tempData.clear();
                mAdapter = new TravelSubjectRecyclerViewAdapter(getActivity(),(ArrayList<TravelSubjectListModel>) mListData);
                mHandler.obtainMessage(MSG_DATAGETSUCCESS).sendToTarget();
            }
        });
    }

    /**
     * 更新网络数据包括上拉加载和下啦刷新
     * @param pageCount
     */
    private void updateData(int pageCount) {
        Request request = new Request.Builder().url(baseUrl+pageCount).build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.obtainMessage(MSG_DATAGETFAILURE).sendToTarget();
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response);
                Type type = new TypeToken<List<TravelSubjectListModel>>(){}.getType();
                tempData = mGson.fromJson(response.body().string(),type);
                mListData.addAll(tempData);
                tempData.clear();
                mHandler.obtainMessage(MSG_UPDATESUCCESS).sendToTarget();
            }
        });
        mRefreshLayout.finishLoadmore();
    }
}
