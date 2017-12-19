package com.example.adminjs.taobao_progect.gouwuche.view;


import com.example.adminjs.taobao_progect.gouwuche.bean.ShopBean;

/**
 * Created by Adminjs on 2017/11/21.
 */
public interface MainViewListener {
    public void success(ShopBean bean);
    public void failure(Exception e);
}
