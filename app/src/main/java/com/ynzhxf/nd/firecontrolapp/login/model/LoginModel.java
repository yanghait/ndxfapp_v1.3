package com.ynzhxf.nd.firecontrolapp.login.model;


import com.trello.rxlifecycle2.android.ActivityEvent;
import com.ynzhxf.nd.firecontrolapp.base.BaseActivity;
import com.ynzhxf.nd.firecontrolapp.base.BaseModel;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.login.LoginActivity;
import com.ynzhxf.nd.firecontrolapp.login.contract.ILoginContract;
import com.ynzhxf.nd.firecontrolapp.network.api.ApiUitls;
import com.ynzhxf.nd.firecontrolapp.network.observer.HttpRxObservable;

import io.reactivex.Observable;

public class LoginModel extends BaseModel<LoginActivity> implements ILoginContract.Model {

    public LoginModel(LoginActivity activity) {
        super(activity);
    }


    public Observable requestLogin(LoginInfoBean loginInfo) {
        return HttpRxObservable.getObservable(ApiUitls.getApi(getActivity()).login(loginInfo.getDeviceUUID(), loginInfo.getDevicePlatform(), loginInfo.getUserName(), loginInfo.getUserPwd(),loginInfo.getKey() , loginInfo.getCode()), getActivity(), ActivityEvent.DESTROY);
    }

    @Override
    public Observable requestGetVerificationCode() {
        return HttpRxObservable.getObservable(ApiUitls.getApi(getActivity()).loginKeyGet(), getActivity(), ActivityEvent.DESTROY);
    }
}
