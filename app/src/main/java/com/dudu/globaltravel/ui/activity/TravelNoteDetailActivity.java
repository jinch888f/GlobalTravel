package com.dudu.globaltravel.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.dudu.globaltravel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelNoteDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.note_detail_back_btn)
    TextView backText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_note_detail);
        ButterKnife.bind(this);
        backText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        onKeyDown(KeyEvent.KEYCODE_BACK, null);
    }
}
