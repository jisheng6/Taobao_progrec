package com.example.adminjs.taobao_progect.touye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adminjs.taobao_progect.R;

public class MainActivity extends AppCompatActivity {
    private int time = 5;
    private TextView text;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if (time>0){
               time--;
               text.setText(time+"秒后跳转");
               handler.sendEmptyMessageDelayed(0,1000);
           }else{
               Intent intent = new Intent(MainActivity.this, CaActivity.class);
               startActivity(intent);
               finish();
           }
        }
    };
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        text = (TextView)findViewById(R.id.text_name);
        handler.sendEmptyMessageDelayed(0,1000);
        image = (ImageView)findViewById(R.id.image);
        //创建一个动画
        Animation animation = new AlphaAnimation(0f,1f);
        //设置动画时间
        animation.setDuration(5000);
        //给图片设置动画
        image.startAnimation(animation);
    }
}
