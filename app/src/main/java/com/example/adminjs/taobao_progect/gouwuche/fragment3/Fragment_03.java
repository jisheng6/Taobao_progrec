package com.example.adminjs.taobao_progect.gouwuche.fragment3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.gouwuche.adapter.ShopAdapter;
import com.example.adminjs.taobao_progect.gouwuche.bean.ShopBean;
import com.example.adminjs.taobao_progect.gouwuche.presenter.MainPresenter;
import com.example.adminjs.taobao_progect.gouwuche.view.MainViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Adminjs on 2017/11/7.
 */
public class Fragment_03 extends Fragment implements MainViewListener {
    @BindView(R.id.third_recyclerview)
    RecyclerView thirdRecyclerview;
    @BindView(R.id.third_allselect)
    CheckBox checkBoxAll;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    TextView thirdSubmit;
    @BindView(R.id.third_pay_linear)
    LinearLayout thirdPayLinear;
    Unbinder unbinder;
    private MainPresenter presenter;
    private ShopAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_03, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new MainPresenter(this);
        presenter.getData();


        adapter = new ShopAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        thirdRecyclerview.setLayoutManager(manager);
        thirdRecyclerview.setAdapter(adapter);


        adapter.setListener(new ShopAdapter.UpdateUiListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {


                checkBoxAll.setChecked(allCheck);
                thirdTotalnum.setText(num);
                thirdTotalprice.setText(total);
            }
        });
        return view;
    }

    @Override
    public void success(ShopBean bean) {
        Log.i("mean",bean.toString());
        adapter.add(bean);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void failure(Exception e) {

        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }


    @OnClick(R.id.third_allselect)
    public void onViewClicked() {

        adapter.selectAll(checkBoxAll.isChecked());


    }
}
