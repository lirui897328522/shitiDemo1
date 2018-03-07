package com.example.shitidemo1.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.shitidemo1.R;
import com.example.shitidemo1.activity.WebActivity;
import com.example.shitidemo1.adapter.MyLoginAdapter;
import com.example.shitidemo1.adapter.MyNewsBean;
import com.example.shitidemo1.bean.LoginBean;
import com.example.shitidemo1.bean.NewsBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadFragment extends Fragment {


    private Banner banner;
    private RecyclerView rv1;
    private RecyclerView rv2;
    private RecyclerView rv3;
    private List<LoginBean> loginList;
    private String path = "http://172.16.54.19:8080/a/shiti1.json";
    private List<NewsBean.ResultBean.DataBean> listData;
    private TextView beijing;

    public HeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_head, container, false);
        initView(view);
        initBanner();
        initRecyclerView1();
        initRecyclerView2();
        return view;
    }

    private void initRecylerView3() {
        MyNewsBean myNewsBean = new MyNewsBean(listData, getActivity());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv3.setLayoutManager(staggeredGridLayoutManager);
        rv3.setAdapter(myNewsBean);
    }

    private void initRecyclerView2() {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient okHttpClient = client.newBuilder()
                .cache(new Cache(getActivity().getCacheDir(), 1024 * 1024))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(path).cacheControl(new CacheControl.Builder().maxStale(20, TimeUnit.MINUTES).build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(string, NewsBean.class);
                listData = newsBean.getResult().getData();
                MyNewsBean myNewsBean = new MyNewsBean(listData, getActivity());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                rv2.setLayoutManager(linearLayoutManager);
                rv2.setAdapter(myNewsBean);
                initRecylerView3();
            }
        });
    }

    private void initRecyclerView1() {
        loginList = new ArrayList<>();
        loginList.add(new LoginBean(R.mipmap.c, "自由行"));
        loginList.add(new LoginBean(R.mipmap.a, "机票"));
        loginList.add(new LoginBean(R.mipmap.h, "签证"));
        loginList.add(new LoginBean(R.mipmap.b, "目的地参团"));
        loginList.add(new LoginBean(R.mipmap.d, "半自由行"));
        loginList.add(new LoginBean(R.mipmap.e, "酒店"));
        loginList.add(new LoginBean(R.mipmap.g, "门票"));
        loginList.add(new LoginBean(R.mipmap.f, "其他"));
        MyLoginAdapter myLoginAdapter = new MyLoginAdapter(loginList, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        rv1.setLayoutManager(gridLayoutManager);
        rv1.setAdapter(myLoginAdapter);
    }

    private void initBanner() {
        List<String> images = new ArrayList<>();
        images.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg");
        images.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg");
        images.add("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_8550569f222233a015e1cd7d70d2f2d4_2_mwpm_03200403.jpg");
        banner.setImageLoader(new PicassoImageLoader());
        banner.setImages(images);
        banner.start();
    }

    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        rv1 = (RecyclerView) view.findViewById(R.id.rv1);
        rv2 = (RecyclerView) view.findViewById(R.id.rv2);
        rv3 = (RecyclerView) view.findViewById(R.id.rv3);
        beijing = (TextView) view.findViewById(R.id.beijing);
        beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
    }

    public class PicassoImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}
