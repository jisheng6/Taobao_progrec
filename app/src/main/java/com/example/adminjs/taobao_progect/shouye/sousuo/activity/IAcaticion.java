package com.example.adminjs.taobao_progect.shouye.sousuo.activity;

import com.example.adminjs.taobao_progect.shouye.sousuo.bean.NetDataBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Adminjs on 2017/12/12.
 *  http://120.27.23.105/product/addCart?source=android&uid=983&pid=45

 */
public interface IAcaticion {
    @GET("product/addCart?source=android")
    Observable<GouBean> getgou(@Query("uid") String uid, @Query("pid") String pid);
    @GET("product/getProductDetail")
    Observable<NetDataBean> getpin(@Query("pid") String pid);
}
