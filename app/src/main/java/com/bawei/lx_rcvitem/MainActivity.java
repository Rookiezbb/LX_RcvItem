package com.bawei.lx_rcvitem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRcv;
    private EditText mEt;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();

    }

    private void getData() {


        OkHttpUtils.get().url("http://api.expoon.com/AppNews/getNewsList/type/1/p/1").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson g = new Gson();
                MyBean myBean = g.fromJson(response, MyBean.class);
                final List<MyBean.DataBean> data = myBean.getData();
                myAdapter = new MyAdapter(data, MainActivity.this);
                mRcv.setAdapter(myAdapter);
                mRcv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                myAdapter.setOnClickLisener(new MyAdapter.OnClickLisener() {
                    @Override
                    public void OnDainji(View v, int position) {
                        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void OnLong(View v, int position) {
                        String eee = mEt.getText().toString();
                        if(!eee.equals("")){
                            Toast.makeText(MainActivity.this, ""+data.get(position).getNews_title()+"haha"+eee, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "wqdesdthfyuewfsghf", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        mRcv = (RecyclerView) findViewById(R.id.rcv);
        mEt = (EditText) findViewById(R.id.et);
    }
}
