package com.example.adminjs.taobao_progect.shouye.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.shouye.bean.DataDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Adminjs on 2017/11/8.
 */
public class MnAdapter extends BaseAdapter{
    List<DataDataBean.DataBean> list1;
    Context context;

    public MnAdapter(Context context, List<DataDataBean.DataBean> list1) {
        this.context = context;
        this.list1 = list1;
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int i) {
        return list1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.item_layout,null);
            holder = new ViewHolder();
            holder.image = (ImageView)view.findViewById(R.id.text_image);
            holder.name = (TextView)view.findViewById(R.id.text_nam);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        final String[] imgdata= list1.get(i).getGoods_img().split("\\|");
        Glide.with(context)
                .load(imgdata[0])
                .into(holder.image);
        holder.name.setText(list1.get(i).getGoods_name());
     //  ImageLoader.getInstance().displayImage(list1.get(i).getGoods_img(),holder.image, ImageloaderUtil.getDefaultOption());

        return view;
    }
    class ViewHolder{
        ImageView image;
        TextView name;
    }
}
