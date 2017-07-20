package com.dudu.globaltravel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by mac on 2017/6/20.
 */

/**
 * 主界面Fragment上Viewpager和对应的Fragment
 */
public class TravelFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public TravelFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    /**
     * 获取对应页面的子Fragment
     * @param position 子Fragment在ViewPager的位置
     * @return 子Fragment
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    /**
     * 获取包含子Fragment的个数
     * @return子 Fragment的个数
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }


    /**
     * 获取对应子Fragment界面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
