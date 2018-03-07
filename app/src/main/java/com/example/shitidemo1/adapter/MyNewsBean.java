package com.example.shitidemo1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shitidemo1.R;
import com.example.shitidemo1.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 李瑞 on 2018/3/4.
 */

public class MyNewsBean extends RecyclerView.Adapter<MyNewsBean.ViewHolder> {
    private List<NewsBean.ResultBean.DataBean> listData;
    private Context context;

    public MyNewsBean(List<NewsBean.ResultBean.DataBean> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyNewsBean.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsBean.ViewHolder holder, int position) {
        Picasso.with(context).load(listData.get(position).getThumbnail_pic_s()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
