package com.ynzhxf.nd.firecontrolapp.login.model;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.ynzhxf.nd.firecontrolapp.base.BaseModel;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.login.SplashActivity;
import com.ynzhxf.nd.firecontrolapp.login.contract.ISplashContract;
import com.ynzhxf.nd.firecontrolapp.network.api.ApiUitls;
import com.ynzhxf.nd.firecontrolapp.network.observer.HttpRxObservable;

import io.reactivex.Observable;

public class SplashModel extends BaseModel<SplashActivity> implements ISplashContract.IModel {
    public SplashModel(SplashActivity activity) {
        super(activity);
    }

    @Override
    public Observable checkVersion() {
        return HttpRxObservable.getObjectObservable(ApiUitls.getApi(getActivity()).getAppCheckVersion(), getActivity(), ActivityEvent.DESTROY);
    }

    @Override
    public Observable checkLoginState(LoginInfoBean loginInfoBean) {
        return HttpRxObservable.getObjectObservable(ApiUitls.getApi(getActivity()).checkLogin(loginInfoBean.getDeviceUUID(), loginInfoBean.getDevicePlatform(), loginInfoBean.getUserName(), loginInfoBean.getToken()), getActivity(), ActivityEvent.DESTROY);
    }
}
