package com.ynzhxf.nd.firecontrolapp.login.contract;

import com.ynzhxf.nd.firecontrolapp.base.IBaseView;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.login.bean.CheckVersionBean;
import com.ynzhxf.nd.firecontrolapp.login.bean.LoginResponeBean;

import io.reactivex.Observable;

public interface ISplashContract {

    interface IModel{
        Observable checkVersion();

        Observable checkLoginState(LoginInfoBean loginInfoBean);
    }

    public interface ISplashView extends IBaseView{
        void toLogin();
        void checkVersionSuccess(CheckVersionBean checkVersionBean);
        void chekLoginStateSuccess(LoginResponeBean userInfo);
    }

     public interface ISplashPresenter{
        void checkVersion();
        void checkLoginState();
        void doCheckLoginState(LoginInfoBean userInfo);
    }

}
