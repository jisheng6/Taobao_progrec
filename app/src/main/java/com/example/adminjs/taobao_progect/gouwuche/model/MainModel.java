package com.example.adminjs.taobao_progect.gouwuche.model;

import com.example.adminjs.taobao_progect.gouwuche.bean.ShopBean;
import com.example.adminjs.taobao_progect.gouwuche.okhttp.AbstractUiCallBack;
import com.example.adminjs.taobao_progect.gouwuche.okhttp.OkhttpUtils;

/**
 * Created by Adminjs on 2017/11/21.
 */
public class MainModel {

    public void getData(final MainModelCallBack callBack) {
        OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/getCarts?uid=4729", new AbstractUiCallBack<ShopBean>() {


            @Override
            public void success(ShopBean bean) {
                callBack.success(bean);
            }

            @Override
            public void failure(Exception e) {
                 callBack.failure(e);
            }
        });
    }

}
