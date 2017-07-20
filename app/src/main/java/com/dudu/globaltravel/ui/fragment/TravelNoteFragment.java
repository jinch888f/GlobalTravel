package com.dudu.globaltravel.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dudu.globaltravel.R;
import com.dudu.globaltravel.TravelFragmentAdapter;
import com.dudu.globaltravel.ui.fragment.TravelNoteListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by mac on 2017/6/20.
 */

public class TravelNoteFragment extends Fragment {
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    private List<Fragment> fragments;
    public TravelNoteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        super.onCreateView(inflater,viewGroup,savedInstanceState);
        View view = inflater.inflate(R.layout.fg_travelnote, viewGroup, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewpager = (ViewPager) view.findViewById(R.id.viewpager);
        initViewPager();
        return view;
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("游记");
        titles.add("专题");
        for(int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }

        if (fragments == null) {
            fragments = new ArrayList<>();

            fragments.add(new TravelNoteListFragment());
            fragments.add(new TravelNoetubjectListFragment());
        }else if (fragments != null && fragments.size() == 0) {
            fragments.add(new TravelNoteListFragment());
            fragments.add(new TravelNoetubjectListFragment());
        }

        TravelFragmentAdapter mAdapter =new TravelFragmentAdapter(getChildFragmentManager(), fragments, titles);
        mViewpager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
