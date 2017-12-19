package com.example.adminjs.taobao_progect.shouye.sousuo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.shouye.sousuo.adapter.MyAdapter;
import com.example.adminjs.taobao_progect.shouye.sousuo.bean.NetDataBean;
import com.example.adminjs.taobao_progect.shouye.sousuo.presenter.MySearchPresenter;
import com.example.adminjs.taobao_progect.shouye.sousuo.view.ISearchView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;


/**
 * Created by Adminjs on 2017/12/6.
 */
public class SearchActivity extends AppCompatActivity implements ISearchView,View.OnClickListener {
    MySearchPresenter presenter = new MySearchPresenter(this, this);
    private Button mChangeBt;
    private EditText mGoodsEt;
    private Button mSearchBt;
    private XRecyclerView mXrv;
    private  int num=1;
    private  int  aa=1;
    Handler handler=new Handler();
    private ImageView image;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        initView();
    }
    private void initView() {
        mChangeBt = (Button) findViewById(R.id.bt_change);
        mChangeBt.setOnClickListener(this);
        mGoodsEt = (EditText) findViewById(R.id.et_goods);
        mSearchBt = (Button) findViewById(R.id.bt_search);
        mSearchBt.setOnClickListener(this);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        image = (ImageView)findViewById(R.id.et_image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

    }

    @Override
    public void ShowView(NetDataBean bean) {
        //view 层 的方法 用来更新ui
            Toast.makeText(SearchActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();

        if(aa % 2 ==1){
            LinearLayoutManager manager=new LinearLayoutManager(SearchActivity.this);
            mXrv.setLayoutManager(manager);
            mChangeBt.setBackgroundResource(R.drawable.lv_icon);
        }

        adapter = new MyAdapter(SearchActivity.this,bean);
//        adapter.setOnItemClickListener(new MyOnClickLister() {
//            @Override
//            public void OnClickListener(View view, int position) {
//                Intent intent = new Intent(SearchActivity.this, TiaoActivity.class);
//                startActivity(intent);
//            }
//        });

        mXrv.setAdapter(adapter);
        //XRecyclerview的上拉下拉方法
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        presenter.getdata(mGoodsEt.getText().toString(),"1");
                        adapter.notifyDataSetChanged();
                        mXrv.refreshComplete();
                    }
                },900);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载
                        num++;
                        presenter.getdata(mGoodsEt.getText().toString(),num+"");
                        adapter.notifyDataSetChanged();
                        mXrv.loadMoreComplete();

                    }
                },900);
            }
        });

    }


    @Override
    public void onClick(View v) {
//点击事件
        switch (v.getId()) {
            case R.id.bt_change:
                //根据aa变量是奇数还是偶数来判断加载那种布局返回那张图片
                aa++;
                if(aa % 2==0){
                    GridLayoutManager manager= new GridLayoutManager(SearchActivity.this,2);
                    mXrv.setLayoutManager(manager);
                    mChangeBt.setBackgroundResource(R.drawable.grid_icon);

                }if(aa % 2 ==1){
                LinearLayoutManager manager=new LinearLayoutManager(SearchActivity.this);
                mXrv.setLayoutManager(manager);
                mChangeBt.setBackgroundResource(R.drawable.lv_icon);
            }
                break;
            case R.id.bt_search:
                //点击搜索按钮时触发presenter的获取数据方法
                presenter.getdata(mGoodsEt.getText().toString(),"1");
                break;
            default:
                break;
        }
    }
    //实现presenter内部的防止内存溢出方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destory();
    }
}
