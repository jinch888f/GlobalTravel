package com.dudu.globaltravel.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dudu.globaltravel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac on 2017/6/20.
 */

public class TravelToolsFragMent extends Fragment {
    public TravelToolsFragMent() {}

    @BindView(R.id.tool_translate_btn)
    Button translateBtn;
    @BindView(R.id.tool_current_conver_btn)
    Button conversBtn;
    @BindView(R.id.tool_keep_account_btn)
    Button keepAccountBtn;
    @BindView(R.id.tool_map_btn)
    Button mapBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_traveltools, viewGroup, false);
        ButterKnife.bind(this,view);
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTranslate(view);
            }
        });
        conversBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCurrentConvers(view);
            }
        });
        keepAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickKeepAccount(view);
            }
        });
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTravelMap(view);
            }
        });
        return view;
    }

    /**
     *XML中的onClick属性只能寻找到Activity中的Button事件，不能找到Fragment中的点击事件
     */
    public void clickTranslate(View view) {
        showToast("翻译功能");
    }

    public void clickCurrentConvers(View view) {
        showToast("实时汇率");
    }

    public void clickKeepAccount(View view) {
        showToast("记账功能");
    }

    public void clickTravelMap(View view) {
        showToast("旅行地图");
    }

    private void showToast(String string) {
        Toast.makeText(getActivity(), string+"正在开发中，敬请期待", Toast.LENGTH_SHORT).show();
    }
}
