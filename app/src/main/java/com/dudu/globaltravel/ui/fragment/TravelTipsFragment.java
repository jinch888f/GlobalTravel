package com.dudu.globaltravel.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudu.globaltravel.R;
import com.dudu.globaltravel.TravelFragmentAdapter;
import com.dudu.globaltravel.bean.TravelNoteListModel;
import com.dudu.globaltravel.bean.TravelTipsListModel;
import com.dudu.globaltravel.bean.TravelipsTModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mac on 2017/6/20.
 */

public class TravelTipsFragment extends Fragment {
    @BindView(R.id.travel_tips_viewpager)
    ViewPager tipsPager;
    @BindView(R.id.travel_tips_tabs)
    TabLayout tipsTab;
    List<Fragment> mFragments;



    public TravelTipsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_traveltips, viewGroup, false);
        ButterKnife.bind(this,view);
        initPagerView();
        return view;
    }

    private void initPagerView() {
        List<String> titles = new ArrayList<>();
        titles.add("国内");
        titles.add("国外");
        for(int i=0;i<titles.size();i++){
            tipsTab.addTab(tipsTab.newTab().setText(titles.get(i)));
        }
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(new TravelTipsInHomeFragment());
            mFragments.add(new TravelTipsOutHomeFragment());
        }else if (mFragments != null && mFragments.size() == 0) {
            mFragments.add(new TravelTipsInHomeFragment());
            mFragments.add(new TravelTipsOutHomeFragment());
        }
        TravelFragmentAdapter mAdapter =new TravelFragmentAdapter(getChildFragmentManager(), mFragments, titles);
        tipsPager.setAdapter(mAdapter);
        tipsTab.setupWithViewPager(tipsPager);
        tipsTab.setTabsFromPagerAdapter(mAdapter);
    }



}
