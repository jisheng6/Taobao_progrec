package com.example.adminjs.taobao_progect.my.view;

import com.example.adminjs.taobao_progect.my.bean.dengluBean;

/**
 * 周旋
 * 2017/11/13  18:33
 */

public interface Iview {
    /**
     * 登录成功
     */
    void onLoginSuccess(dengluBean deng);

    /**
     * 登录失败
     *
     * @param error
     */
    void onLoginFailed(String error);
}
