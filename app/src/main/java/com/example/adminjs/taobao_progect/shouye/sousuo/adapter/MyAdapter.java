package com.example.adminjs.taobao_progect.shouye.sousuo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.shouye.acticity.TiaoActivity;
import com.example.adminjs.taobao_progect.shouye.sousuo.bean.NetDataBean;
import com.example.adminjs.taobao_progect.shouye.sousuo.util.MyOnClickLister;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 *适配器
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myholder> {
    //两个参数  上下文，和数据源
    Context context;
    NetDataBean bean;
    private MyOnClickLister itemClickListener;

    public MyAdapter(Context context, NetDataBean bean) {
        this.context = context;
        this.bean = bean;
    }
    public void setOnItemClickListener(MyOnClickLister itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局返回到holder

        View view=View.inflate(context, R.layout.lin_layout,null);
        myholder myholder=new myholder(view);

        return myholder;
    }

    @Override
    public void onBindViewHolder(final myholder holder, final int position) {
        //通过数据源内的图片字符串 通过split截取成数组
         final String[] imgdata=bean.getData().get(position).getImages().split("\\|");
         Glide.with(context)
                 .load(imgdata[0])
                 .into(holder.item_li_iv);
        //holder.item_li_iv.setImageURI(bean.getData().get(position).getImages());
        //设置控件 属性
         holder.item_li_tv1.setText(bean.getData().get(position).getTitle());
         holder.item_li_tv2.setText(bean.getData().get(position).getPrice()+"￥");
//        holder.item_li_tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (bean != null){
//                    Intent intent = new Intent(context,ZhuanActivity.class);
//
//                    intent.putExtra("url",bean.getData().get(position).getDetailUrl());
//                    context.startActivity(intent);
//                }
//            }
//        });
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.OnClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        holder.item_li_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TiaoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("pid",bean.getData().get(position).getPid()+"");
                bundle.putString("image", bean.getData().get(position).getImages());
                bundle.putString("name",bean.getData().get(position).getTitle());
                bundle.putString("subhead",bean.getData().get(position).getSubhead());
                bundle.putString("price",bean.getData().get(position).getPrice()+"");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return bean.getData()==null?0:bean.getData().size();
    }
    class myholder extends RecyclerView.ViewHolder{
        SimpleDraweeView item_li_iv;
        TextView item_li_tv1;
        TextView item_li_tv2;
        public myholder(View itemView) {
            super(itemView);
            item_li_iv= (SimpleDraweeView) itemView.findViewById(R.id.item_li_iv);
            item_li_tv1= (TextView) itemView.findViewById(R.id.item_li_tv1);
            item_li_tv2= (TextView) itemView.findViewById(R.id.item_li_tv2);
        }
    }

}
