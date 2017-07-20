package com.dudu.globaltravel.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dudu.globaltravel.R;
import com.dudu.globaltravel.TravelFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {
    private long exitTime = 0;
    private ViewPager vpager;
    private TravelFragmentPagerAdapter mAdapter;
    //UI Objects
    private TextView txt_topbar;
    private RadioGroup  rg_tab_bar;
    private RadioButton rb_travelNote;
    private RadioButton rb_traveltips;
    private RadioButton rb_traveltools;
    private RadioButton rb_mine;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new TravelFragmentPagerAdapter(getSupportFragmentManager());
        initViews();
        rb_travelNote.setChecked(true);


    }

    private void initViews() {
        rg_tab_bar = (RadioGroup)findViewById(R.id.rg_tab_bar);
        txt_topbar = (TextView) findViewById(R.id.txt_top_bar_title);
        rb_travelNote = (RadioButton)findViewById(R.id.location);
        rb_traveltips = (RadioButton)findViewById(R.id.feature);
        rb_traveltools = (RadioButton)findViewById(R.id.tool);
        rb_mine = (RadioButton)findViewById(R.id.mine);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager)findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.location:
                vpager.setCurrentItem(PAGE_ONE);
                txt_topbar.setText("游记");
                break;
            case R.id.feature:
                vpager.setCurrentItem(PAGE_TWO);
                txt_topbar.setText("攻略");
                break;
            case R.id.tool:
                vpager.setCurrentItem(PAGE_THREE);
                txt_topbar.setText("工具");
                break;
            case R.id.mine:
                vpager.setCurrentItem(PAGE_FOUR);
                txt_topbar.setText("我的");
                break;
        }
    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_travelNote.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_traveltips.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_traveltools.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_mine.setChecked(true);
                    break;

            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
