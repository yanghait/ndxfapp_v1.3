package com.ynzhxf.nd.firecontrolapp.login.contract;

import com.ynzhxf.nd.firecontrolapp.base.IBaseView;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.login.bean.LoginResponeBean;

import io.reactivex.Observable;

public interface ILoginContract {

    interface Model {
        Observable requestLogin(LoginInfoBean loginInfoBean);

        Observable requestGetVerificationCode();
    }

    public interface ILoginView extends IBaseView {

        void getVerificationCodeSuccess(String identifyKey);

        void loginSuccess(LoginResponeBean userInfo);
    }

    interface ILoginPresenter {
        void getVerificationCode();

        void login(LoginInfoBean loginInfoBean);
    }

}
