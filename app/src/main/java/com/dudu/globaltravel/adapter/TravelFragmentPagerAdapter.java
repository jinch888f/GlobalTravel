package com.dudu.globaltravel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.dudu.globaltravel.ui.activity.MainActivity;
import com.dudu.globaltravel.ui.fragment.*;

/**
 * Created by mac on 2017/6/20.
 */

/**
 * 主界面Fragment上Viewpager和对应的Fragment
 */
public class TravelFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 4;
    private TravelNoteFragment travelNoteFragment;
    private TravelTipsFragment travelTipsFragment;
    private TravelToolsFragMent travelToolsFragMent;
    private MineFragment mineFragment;

    public TravelFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        travelNoteFragment = new TravelNoteFragment();
        travelTipsFragment = new TravelTipsFragment();
        travelToolsFragMent = new TravelToolsFragMent();
        mineFragment = new MineFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg,position);
    }

    @Override
    public void destroyItem(ViewGroup vg, int position, Object obj) {
        super.destroyItem(vg, position,obj);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case MainActivity.PAGE_ONE:
                fragment = travelNoteFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = travelTipsFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = travelToolsFragMent;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = mineFragment;
                break;
        }
        return fragment;
    }

}
