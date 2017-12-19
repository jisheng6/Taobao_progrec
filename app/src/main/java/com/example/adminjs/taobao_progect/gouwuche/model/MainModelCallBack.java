package com.example.adminjs.taobao_progect.gouwuche.model;

import com.example.adminjs.taobao_progect.gouwuche.bean.ShopBean;

/**
 * Created by Adminjs on 2017/11/21.
 */
public interface MainModelCallBack {

   public void success(ShopBean bean);
   public void failure(Exception e);
}
