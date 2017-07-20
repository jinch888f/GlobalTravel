package com.dudu.globaltravel.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dudu.globaltravel.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mac on 2017/6/20.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.my_info)
    AppCompatTextView myInfoTextView;
    @BindView(R.id.feedback)
    AppCompatTextView myFeedbackTextView;
    @BindView(R.id.my_about) AppCompatTextView myAboutTextView;
    @BindView(R.id.traveler_image)
    CircleImageView iconImage;
    public MineFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mine, viewGroup, false);
        ButterKnife.bind(this,view);
        myAboutTextView.setOnClickListener(this);
        myFeedbackTextView.setOnClickListener(this);
        myInfoTextView.setOnClickListener(this);
        iconImage.setOnClickListener(this);
        return view;
    }

    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_info:
                showToast("我的消息");
                break;
            case R.id.feedback:
                showToast("意见反馈");
                break;
            case R.id.my_about:
                showToast("关于APP");
                break;
            case R.id.traveler_image:
                showToast("用户头像");
                break;
        }
    }

    private void showToast(String string) {
        Toast.makeText(getActivity(), string+"被点击了", Toast.LENGTH_SHORT).show();
    }

}
