package com.dudu.globaltravel.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudu.globaltravel.R;
import com.dudu.globaltravel.adapter.TravelTipsOutHomeListAdapter;
import com.dudu.globaltravel.bean.TravelTipsListModel;
import com.dudu.globaltravel.bean.TravelipsTModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
 * Created by mac on 2017/7/13.
 */

public class TravelTipsOutHomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<TravelTipsListModel> mModels;
    private List<TravelipsTModel> mData;

    private OkHttpClient mClient;
    private Gson mGson;

    private static final int MSG_DATAGETSUCCESS = 0;
    private static final int MSG_DATAGETFAILURE = 1;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_DATAGETSUCCESS:
                    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                    mRecyclerView.setAdapter(new TravelTipsOutHomeListAdapter(getActivity(),mModels));
                    break;
                case MSG_DATAGETFAILURE:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fg_traveltipsouthome, parent, false);
        mClient = new OkHttpClient();
        mGson = new Gson();
        mModels = new ArrayList<>();
        getData();
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void getData() {
        final Request request = new Request.Builder().url("http://chanyouji.com/api/destinations.json?page=1").build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<List<TravelipsTModel>>(){}.getType();
                mData = mGson.fromJson(response.body().string(),type);
                mModels = new ArrayList<>();
                for (int i = 0; i<5; i++){
                    if (mData.get(i).category.equals("1") || mData.get(i).category.equals("2") || mData.get(i).category.equals("3")) {
                        mModels.addAll(mData.get(i).destinations);
                        mHandler.obtainMessage(MSG_DATAGETSUCCESS).sendToTarget();
                    }
                }

            }
        });
    }

}
