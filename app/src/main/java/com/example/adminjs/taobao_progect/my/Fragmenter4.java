package com.example.adminjs.taobao_progect.my;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminjs.taobao_progect.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Adminjs on 2017/12/8.
 */
public class Fragmenter4 extends Fragment {
    //初始化
    @BindView(R.id.login)
    ImageView login;
    Unbinder unbinder;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    SharedPreferences sp;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.allpay)
    TextView allpay;

    //mine页面
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_04, null);
        unbinder = ButterKnife.bind(this, view);
        //注册事件
        EventBus.getDefault().register(this);
        return view;
    }

    //销毁视图
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.login, R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent intent = new Intent(getActivity(), Denglu_yemian.class);
                startActivity(intent);
                break;
            case R.id.ll_back:
                sp = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE);
                sp.edit().clear().commit();
                System.out.println("清空：" + sp.getString("uid", null));
                username.setText("用户名");
                Toast.makeText(getActivity(), "已退出", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent mess) {
        username.setText(mess.getMessage());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.allpay)
    public void onViewClicked() {



    }
}
