package com.example.adminjs.taobao_progect.shouye.acticity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.shouye.sousuo.model.AddCartBean;
import com.example.adminjs.taobao_progect.shouye.sousuo.model.AddCartModelCallBack;
import com.example.adminjs.taobao_progect.shouye.sousuo.model.AddCartPresenter;
import com.example.adminjs.taobao_progect.shouye.sousuo.model.AddCartViewCallBack;
import com.example.adminjs.taobao_progect.shouye.sousuo.util.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adminjs on 2017/12/7.
 */
public class TiaoActivity extends Activity implements AddCartModelCallBack, AddCartViewCallBack {
    @BindView(R.id.image)
    Banner banner;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.subhead)
    TextView subhead;
    @BindView(R.id.jia)
    Button jia;
    @BindView(R.id.gou)
    Button gou;
    private AddCartPresenter addCartPresenter;
    private int pid;
    private String pid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_xiang);
        ButterKnife.bind(this);

        Bundle buddle = getIntent().getExtras();
        String image1 = buddle.getString("image");

        pid1 = buddle.getString("pid");

        String name1 = buddle.getString("name");
        String subhead1 = buddle.getString("subhead");
        String price1 = buddle.getString("price");

        final String[] split = image1.split("\\|");

        banner.setImageLoader(new GlideImageLoader());
        List<String> bannerList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            bannerList.add(split[i]);
        }
        System.out.println("banner中：："+bannerList.get(0));
        banner.setImages(bannerList);
        banner.start();


 //http://120.27.23.105/product/addCart?source=android&uid=983&pid=45

        name.setText(name1);
        subhead.setText(subhead1);
        price.setText("￥" + price1);
        addCartPresenter = new AddCartPresenter(this);


        gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TiaoActivity.this, "正在跳转....", Toast.LENGTH_SHORT).show();
            }
        });

        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Retrofit retrofit =  new Retrofit.Builder()
//                        .baseUrl("http://120.27.23.105")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                        .build();
//                IAcaticion iAcaticion = retrofit.create(IAcaticion.class);
//                iAcaticion.getgou(4729+"","45").subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<GouBean>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(GouBean gouBean) {
//                                String code = gouBean.getCode();
//                                if (code.equals("0")){
//
//                                    Toast.makeText(TiaoActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
//                                }else{
//                                    Toast.makeText(TiaoActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
                addCartPresenter.getData(pid1);


            }
            });
    }

    @Override
    public void success(AddCartBean addCartBean) {
        Toast.makeText(this,""+addCartBean.getMsg(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void failure() {
        Toast.makeText(this,""+"加入失败",Toast.LENGTH_LONG).show();

    }
}
