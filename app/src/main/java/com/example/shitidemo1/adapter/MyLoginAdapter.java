package com.example.shitidemo1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shitidemo1.R;
import com.example.shitidemo1.bean.LoginBean;

import java.util.List;

/**
 * Created by 李瑞 on 2018/3/4.
 */

public class MyLoginAdapter extends RecyclerView.Adapter<MyLoginAdapter.ViewHolder> {
    private List<LoginBean> loginList;
    private Context context;

    public MyLoginAdapter(List<LoginBean> loginList, Context context) {
        this.loginList = loginList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyLoginAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.login,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyLoginAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(loginList.get(position).getImage());
        holder.name.setText(loginList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return loginList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.tv);
        }
    }
}
