package com.example.adminjs.taobao_progect.my.model;


import android.util.Log;

import com.example.adminjs.taobao_progect.my.Api;
import com.example.adminjs.taobao_progect.my.ApiServer;
import com.example.adminjs.taobao_progect.my.bean.User;
import com.example.adminjs.taobao_progect.my.bean.dengluBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 周旋
 * 2017/11/13  18:39
 */

public class UserMode implements Imodel {

    private OnFinish onFinish;

    public interface OnFinish{
        void OnFinishListener(dengluBean dengluBean);
    }
    public void setOnFinish(OnFinish finish){
        this.onFinish=finish;
    }
    @Override
    public void login(User user) {
//        if (dengluBean.getCode().equals("0")){
//            return "true";
//        }else{
//            return "false";
//        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.DENGLU_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
//        Map<String ,String> map = new HashMap<>();
//        map.put("mobile",user.getMobile());
//        map.put("password",user.getPassword());
        Observable<dengluBean> home = apiService.login(user.getMobile(),user.getPassword());
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<dengluBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(dengluBean dengluBean) {

                        Log.e("main","code:"+dengluBean.getCode());
                        onFinish.OnFinishListener(dengluBean);

                    }
                });
    }
}


