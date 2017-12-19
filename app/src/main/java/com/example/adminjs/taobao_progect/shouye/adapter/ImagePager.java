package com.example.adminjs.taobao_progect.shouye.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminjs.taobao_progect.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

/**
 * @author Dash
 * @date 2017/9/12
 * @description:
 */
public class ImagePager extends PagerAdapter {
    Context context;
    List<String> list;
    Handler handler;

    public ImagePager(Context context, List<String> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;

        //初始化
       ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //返回当前显示的视图
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //加载网络的图片

        ImageLoader.getInstance().displayImage(list.get(position%list.size()),imageView,getOption());

        //imageView触摸的监听事件
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean isTiaoZhuan = true;

                switch (motionEvent.getAction()){//获取动作
                    case MotionEvent.ACTION_DOWN://按下
                        Log.i("--","ACTION_DOWN");
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE://移动
                        Log.i("--","ACTION_MOVE");
                        isTiaoZhuan = false;
                        handler.removeCallbacksAndMessages(null);

                        break;
                    case MotionEvent.ACTION_CANCEL://取消
                        Log.i("--","ACTION_CANCEL");
                        isTiaoZhuan = false;
                        handler.sendEmptyMessageDelayed(0,2000);
                        break;
                    case MotionEvent.ACTION_UP://抬起
                        Log.i("--","ACTION_UP");
                        if (isTiaoZhuan){
                            Toast.makeText(context,"跳转页面",Toast.LENGTH_SHORT).show();

                            /*Intent intent = new Intent();

                            context.startActivity();*/
                        }
                        handler.sendEmptyMessageDelayed(0,2000);

                        break;
                }

                //自己处理触摸事件....如果当前位置返回了true,,,点击事件将不会执行,,,表示动作自己处理,不会传递
                return true;
            }
        });

        //添加到容器
        container.addView(imageView);

        return imageView;
    }
    private DisplayImageOptions getOption() {
        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .resetViewBeforeLoading(true)//在加载之前复位一下显示
                .bitmapConfig(Bitmap.Config.RGB_565)//图片的质量
                .considerExifParams(true)///是否考虑JPEG图像EXIF参数（旋转，翻转）
                .build();
        return imageOptions;


    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}