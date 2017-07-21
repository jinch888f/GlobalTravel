package com.dudu.globaltravel.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.dudu.globaltravel.R;
import com.dudu.globaltravel.adapter.NoteListDetailAdapter;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TravelNoteDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.note_detail_back_btn)
    TextView backText;
    @BindView(R.id.note_detail_recyclerView)
    RecyclerView mRecyclerView;

    private int t_id;
    private final String baseUrl = "http://chanyouji.com/api/trips/";
    private final String urlSuffix = ".json";
    private OkHttpClient mClient;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_note_detail);
        ButterKnife.bind(this);
        configRecyclerView();
        backText.setOnClickListener(this);
        t_id = getT_id();
        mClient = new OkHttpClient();
        mGson = new Gson();
        getData();
    }

    private int getT_id() {
        Bundle bundle = this.getIntent().getExtras();
        int t_id = bundle.getInt("t_id");
        return t_id;
    }

    private void getData() {
        Request request = new Request.Builder().url(baseUrl+t_id+urlSuffix).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 System.out.println(response);
            }
        });
    }

    private void configRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new NoteListDetailAdapter(this));
    }
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}
