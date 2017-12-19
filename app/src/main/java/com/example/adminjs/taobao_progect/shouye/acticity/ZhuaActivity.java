package com.example.adminjs.taobao_progect.shouye.acticity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminjs.taobao_progect.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adminjs on 2017/12/11.
 */
public class ZhuaActivity extends Activity {

    @BindView(R.id.uname)
    TextView uname;
    @BindView(R.id.usubhead)
    TextView usubhead;
    @BindView(R.id.uprice)
    TextView uprice;
    @BindView(R.id.che)
    Button che;
    @BindView(R.id.ji)
    Button ji;
    @BindView(R.id.uimage)
    SimpleDraweeView uimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_xian);
        ButterKnife.bind(this);
        Bundle buddle = getIntent().getExtras();
        String image1 = buddle.getString("imag");
        String name1 = buddle.getString("name");
        String subhead1 = buddle.getString("subhead");
        String price1 = buddle.getString("price");
        uimage.setImageURI(image1);
        uname.setText(name1);
        usubhead.setText(subhead1);
        uprice.setText("￥" + price1);
        ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZhuaActivity.this, "正在跳转....", Toast.LENGTH_SHORT).show();
            }
        });

        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ZhuaActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
