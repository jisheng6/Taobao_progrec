package com.example.adminjs.taobao_progect.gouwuche.presenter;


import com.example.adminjs.taobao_progect.gouwuche.bean.ShopBean;
import com.example.adminjs.taobao_progect.gouwuche.model.MainModel;
import com.example.adminjs.taobao_progect.gouwuche.model.MainModelCallBack;
import com.example.adminjs.taobao_progect.gouwuche.view.MainViewListener;

/**
 * Created by Adminjs on 2017/11/21.
 */
public class MainPresenter {
    private MainViewListener listener;
    private MainModel mainModel;

    public MainPresenter(MainViewListener listener) {
        this.listener = listener;
        this.mainModel = new MainModel();
    }

    public void getData(){
        mainModel.getData(new MainModelCallBack() {
            @Override
            public void success(ShopBean bean) {
                if (listener != null){
                    listener.success(bean);
                }
            }

            @Override
            public void failure(Exception e) {
                 if (listener != null){
                     listener.failure(e);
                 }
            }
        });
    }
    public void detach(){
        listener = null;
    }
}
