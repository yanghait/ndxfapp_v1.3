package com.ynzhxf.nd.firecontrolapp.presenter.platform.impl;

import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.model.Platform.PlatformModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.platform.ILoginPersenter;

import java.util.Map;

/**
 *
 * Created by nd on 2018-07-13.
 */

class LoginPersenterImpl extends BasePersenter implements ILoginPersenter {
    private ILoginPersenter.ILoginModel model;
    private ILoginPersenter.ILoginView view;

    public LoginPersenterImpl(ILoginPersenter.ILoginView view){
        this.view = view;
        model = PlatformModelFactory.getLoginModel(this);
    }


    @Override
    public void callBackError(ResultBean<String, String> result,String action) {

        if(view!=null){
            view.callBackError(result,action);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void dologin(LoginInfoBean bean) {
        model.requestLogin(bean);
    }

    @Override
    public void callBackLogin(ResultBean<LoginInfoBean, Map<String, String>> result) {
        if(view != null){
            view.callBackLogin(result);
        }

    }

}
