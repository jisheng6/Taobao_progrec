package com.example.adminjs.taobao_progect.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.my.bean.DingdanBean;

import java.util.List;

/**
 * 周旋
 * 2017/11/22  18:37
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<DingdanBean.DataBean> list;

    public HomeAdapter(Context context, List<DingdanBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.dingdan_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder) holder;
        myViewHolder.tv.setText(list.get(position).getCreatetime());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;

        //        SimpleDraweeView sdv;
        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
