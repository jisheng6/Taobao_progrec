package com.example.adminjs.taobao_progect.shouye.fragment_shou;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.shouye.acticity.ZhuaActivity;
import com.example.adminjs.taobao_progect.shouye.adapter.ImagePager;
import com.example.adminjs.taobao_progect.shouye.adapter.MnAdapter;
import com.example.adminjs.taobao_progect.shouye.adapter.MyGridAdapter;
import com.example.adminjs.taobao_progect.shouye.bean.DataDataBean;
import com.example.adminjs.taobao_progect.shouye.sousuo.activity.SearchActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Adminjs on 2017/11/7.
 */
public class Fragment_01 extends Fragment {
    private ViewPager pager;
    private LinearLayout iv_sao;
    private EditText iv_sou;
    private LinearLayout linearLayout;
    private List<String> listT = new ArrayList<>();
    private List<Integer> imgT = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                int currentItem = pager.getCurrentItem();
                pager.setCurrentItem(currentItem+1);

                handler.sendEmptyMessageDelayed(0,2000);
            }
        }
    };
    private int page_num;
    private List<String> list;
    private List<ImageView> images;
    private GridView gridview;
    private ArrayList<DataDataBean.DataBean> list1 = new ArrayList<>();
    private MnAdapter mnAdapter;
    private PullToRefreshGridView gridvie;
    // private GridView gridvie;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_01, container, false);
        pager = (ViewPager)view.findViewById(R.id.view_pager);
        iv_sao = (LinearLayout) view.findViewById(R.id.iv_sao);
        iv_sou = (EditText) view.findViewById(R.id.iv_sou);
        linearLayout = (LinearLayout)view.findViewById(R.id.linear_layout);
        gridview = (GridView) view.findViewById(R.id.gv_fragment);
        ViewFlipper vf = (ViewFlipper)view.findViewById(R.id.view_filpper);
        vf.addView(View.inflate(getActivity(),R.layout.item_paomadeng,null));
      //  gridvie = (GridView)view.findViewById(R.id.gridview);
        gridvie = (PullToRefreshGridView)view.findViewById(R.id.refresh_grid_view);
//        gridvie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (list1 != null){
//                    Intent intent = new Intent(getActivity(),WebActivity.class);
//
//                    intent.putExtra("url",list1.get(i).getWatermarkUrl());
//                    startActivity(intent);
//                }
//            }
//        });
       gridvie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(getActivity(), ZhuaActivity.class);
               Bundle bundle = new Bundle();
               bundle.putString("imag",list1.get(i).getGoods_img());
               bundle.putString("name",list1.get(i).getGoods_name());
               bundle.putString("subhead",list1.get(i).getEfficacy());
               bundle.putString("price",list1.get(i).getShop_price()+"");
               intent.putExtras(bundle);
               startActivity(intent);
           }
       });
        listT.add("天猫");
        listT.add("聚划算");
        listT.add("天猫国际");
        listT.add("外卖");
        listT.add("天猫超市");
        listT.add("充值中心");
        listT.add("飞猪旅行");
        listT.add("领金币");
        listT.add("拍卖");
        listT.add("分类");
        imgT.add(R.mipmap.a);
        imgT.add(R.mipmap.b);
        imgT.add(R.mipmap.c);
        imgT.add(R.mipmap.d);
        imgT.add(R.mipmap.e);
        imgT.add(R.mipmap.f);
        imgT.add(R.mipmap.g);
        imgT.add(R.mipmap.h);
        imgT.add(R.mipmap.i);
        imgT.add(R.mipmap.j);
        MyGridAdapter myGridAdapter = new MyGridAdapter(listT,imgT,getActivity());
        gridview.setAdapter(myGridAdapter);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add("https://img.alicdn.com/tps/i4/TB1lJ2QkBcHL1JjSZJiSuwKcpXa.jpg_1080x1800Q60s50.jpg");
        list.add("https://img.alicdn.com/tps/i4/TB12HAtgS7PL1JjSZFHSuwciXXa.jpg_1080x1800Q60s50.jpg");
        list.add("https://img.alicdn.com/tps/i4/TB1okwlX2BNTKJjSszcSuvO2VXa.jpg_1080x1800Q60s50.jpg");
        list.add("https://img.alicdn.com/tfs/TB1G9L1b2BNTKJjy0FdXXcPpVXa-990-450.jpg_1080x1800Q60s50.jpg");
        list.add("https://img.alicdn.com/tps/i4/TB1xuMWbhuaVKJjSZFjSuwjmpXa.jpg_1080x1800Q60s50.jpg");
        initDoc();
        ImagePager imagePager = new ImagePager(getActivity(), list,handler);
        //
        pager.setAdapter(imagePager);

        //设置ViewPager初始展示的位置
        pager.setCurrentItem(list.size()*10000);


        //发送延时消息
        handler.sendEmptyMessageDelayed(0,2000);


        //viewPager页面改变的监听事件
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //如果选中了这一页,,,当前小圆点红色,,,否则绿色
                for (int i = 0;i<images.size();i++){
                    if (i== position%images.size()){
                        images.get(i).setImageResource(R.drawable.doc_select);
                    }else {
                        images.get(i).setImageResource(R.drawable.doc_select_no);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化小圆点
     */
    private void initDoc() {
        //首先需要一个集合记录这些小圆点的图片,,,,当页面切换的时候,可以从集合中取出imageView进行显示图片的设置
        images = new ArrayList<>();
        linearLayout.removeAllViews();//清空/移除所有的view

        for (int i = 0;i<list.size();i++){
            ImageView imageView = new ImageView(getActivity());

            if (i==0){//显示第一页,,,红的
                imageView.setImageResource(R.drawable.doc_select);
            }else {//绿的
                imageView.setImageResource(R.drawable.doc_select_no);
            }

            //添加到集合
            images.add(imageView);

            //加入到线性布局中显示
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(5,0,5,0);

            linearLayout.addView(imageView,params);

        }

        getDataFromNet();
        gridvie.setMode(PullToRefreshBase.Mode.BOTH);

        ILoadingLayout startLabels = gridvie.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在刷新...");
        startLabels.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = gridvie.getLoadingLayoutProxy( false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");
        gridvie.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                getDataFromNet();
                refreshView.onRefreshComplete();

            }
        });


    //点击扫一扫监听
        iv_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接跳转到依赖包里面   没有吐司
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);
            }
        });
        //点击搜索监听
        iv_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getDataFromNet() {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                String path = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id=9";
                try {
                    URL url = new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    //获取
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();

                        String json = streamToString(inputStream,"utf-8");

                        return json;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return "";
            }

            @Override
            protected void onPostExecute(String json) {

                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                if (dataDataBean != null){
                    list1.addAll(0,dataDataBean.getData());
                    setAdapter();

                }else{
                    Toast.makeText(getActivity(),"数据不能为空",Toast.LENGTH_SHORT).show();
                }
            }


        };

        asyncTask.execute();

    }
    private void setAdapter() {
        if (mnAdapter == null){
            mnAdapter = new MnAdapter(getActivity(), list1);
            gridvie.setAdapter(mnAdapter);
        }else{
            mnAdapter.notifyDataSetChanged();
        }

    }

    private String streamToString(InputStream inputStream,String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null){
                builder.append(s);
            }
          //  Log.i("------",builder.toString());

            bufferedReader.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
}
