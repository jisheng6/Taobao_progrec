package com.example.adminjs.taobao_progect.my;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.my.bean.User;
import com.example.adminjs.taobao_progect.my.bean.dengluBean;
import com.example.adminjs.taobao_progect.my.parsenter.UserPresenter;
import com.example.adminjs.taobao_progect.my.view.Iview;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Denglu_yemian extends AppCompatActivity implements Iview {
    //初始化属性
    @BindView(R.id.btn_denglu)
    Button btnDenglu;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv)
    TextView tv;

    private UserPresenter userPresenter;
    SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu_yemian);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        userPresenter = new UserPresenter(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();

    }

    //登录按钮和注册的点击事件
    @OnClick({R.id.btn_denglu, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_denglu:
                username = etUserName.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                userPresenter.login(new User(username, password));
                break;
            case R.id.tv_register:
                Intent intent = new Intent(Denglu_yemian.this, Register_yemian.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onLoginSuccess(dengluBean deng) {
        String uid = deng.getData().getUid();

        editor.putString("uid", uid);

        editor.commit();
        EventBus.getDefault().post(new MessageEvent(username.toString()));
        tv = (TextView) findViewById(R.id.tv);
        img = (ImageView) findViewById(R.id.img);

        Toast.makeText(getApplicationContext(), "登录成功:", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Log.d("uid为:", deng.getData().getUid());

    }

    @Override
    public void onLoginFailed(String error) {
        Toast.makeText(getApplicationContext(), "登录失败:" + error, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
