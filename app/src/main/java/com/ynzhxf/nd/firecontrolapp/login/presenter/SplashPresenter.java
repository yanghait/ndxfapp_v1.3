package com.ynzhxf.nd.firecontrolapp.login.presenter;

import com.google.gson.Gson;
import com.ynzhxf.nd.firecontrolapp.base.BasePresenter;
import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.login.SplashActivity;
import com.ynzhxf.nd.firecontrolapp.login.bean.CheckLoginBean;
import com.ynzhxf.nd.firecontrolapp.login.bean.CheckVersionBean;
import com.ynzhxf.nd.firecontrolapp.login.bean.LoginResponeBean;
import com.ynzhxf.nd.firecontrolapp.login.contract.ISplashContract;
import com.ynzhxf.nd.firecontrolapp.login.model.SplashModel;
import com.ynzhxf.nd.firecontrolapp.network.exception.ApiException;
import com.ynzhxf.nd.firecontrolapp.network.observer.HttpRxObserver;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class SplashPresenter extends BasePresenter<ISplashContract.ISplashView, SplashActivity> implements ISplashContract.ISplashPresenter {

    SplashModel splashModel;

    public SplashPresenter(ISplashContract.ISplashView view, SplashActivity activity) {
        super(view, activity);
        splashModel = new SplashModel(activity);
    }

    @Override
    public void checkVersion() {
        HttpRxObserver httpRxObserver = new HttpRxObserver() {
            @Override
            protected void onStart(Disposable d) {
                getView().showLoading();
            }

            @Override
            protected void onError(ApiException e) {
                getView().showToast(e.getMsg());
            }

            @Override
            protected void onSuccess(Object response) {
                CheckVersionBean checkVersionBean = gson.fromJson(response.toString(), CheckVersionBean.class);
                getView().checkVersionSuccess(checkVersionBean);
            }
        };
        splashModel.checkVersion().subscribe(httpRxObserver);
    }

    @Override
    public void checkLoginState() {
        LoginResponeBean loginResponeBean = gson.fromJson(preferences.getUserInfo(), LoginResponeBean.class);
        if (loginResponeBean != null) {
            LoginInfoBean n = new LoginInfoBean();
            n.setUserName(loginResponeBean.getUserName());
            n.setDeviceUUID(loginResponeBean.getDeviceUUID());
            n.setDevicePlatform(loginResponeBean.getDevicePlatform());
            n.setUserPwd(loginResponeBean.getUserPsw());
            n.setToken(loginResponeBean.getToken());
            doCheckLoginState(n);
        } else {
            getView().toLogin();
        }
    }

    public void doCheckLoginState(LoginInfoBean loginInfoBean) {
        HttpRxObserver httpRxObserver = new HttpRxObserver() {
            @Override
            protected void onStart(Disposable d) {
                getView().showLoading();
            }

            @Override
            protected void onError(ApiException e) {
                getView().showToast(e.getMsg());
            }

            @Override
            protected void onSuccess(Object response) {
                CheckLoginBean checkLoginBean = gson.fromJson(response.toString(), CheckLoginBean.class);
                if (checkLoginBean.isLogin()) {
                    LoginResponeBean loginResponeBean = gson.fromJson(preferences.getUserInfo(), LoginResponeBean.class);
                    getView().chekLoginStateSuccess(loginResponeBean);
                } else {
                    getView().toLogin();
                }

            }
        };
        splashModel.checkLoginState(loginInfoBean).subscribe(httpRxObserver);
    }
}
