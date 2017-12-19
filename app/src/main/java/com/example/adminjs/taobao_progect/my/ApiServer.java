package com.example.adminjs.taobao_progect.my;




import com.example.adminjs.taobao_progect.my.bean.dengluBean;
import com.example.adminjs.taobao_progect.my.bean.zhuceBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
     * Created by fan on 2017/11/8.
 */

public interface ApiServer {





    @FormUrlEncoded
    @POST("user/login")
    Observable<dengluBean> login(@Field("mobile") String mobile, @Field("password") String password);





 @FormUrlEncoded
    @POST("user/reg")
    Observable<zhuceBean> logina(@Field("mobile") String mobile, @Field("password") String password);


}
