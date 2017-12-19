package com.example.adminjs.taobao_progect.my.parsenter;


import com.example.adminjs.taobao_progect.my.bean.User;
import com.example.adminjs.taobao_progect.my.bean.dengluBean;
import com.example.adminjs.taobao_progect.my.model.UserMode;
import com.example.adminjs.taobao_progect.my.view.Iview;

/**
 * 周旋
 * 2017/11/13  18:39
 */

public class UserPresenter implements UserMode.OnFinish{
    private final Iview userView;
    private final UserMode userMode;

    public UserPresenter(Iview iview) {
        this.userView = iview;
        this.userMode = new UserMode();
        userMode.setOnFinish(this);
    }
    public void login(final User user) {
        userMode.login(user);
    }

    @Override
    public void OnFinishListener(dengluBean dengluBean) {
        String code = dengluBean.getCode();
            if (code.equals("0")) {
            userView.onLoginSuccess(dengluBean);
        } else {
            userView.onLoginFailed("请求失败");

        }
    }
}
