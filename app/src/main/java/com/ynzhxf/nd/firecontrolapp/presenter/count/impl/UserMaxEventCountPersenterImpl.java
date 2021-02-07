package com.ynzhxf.nd.firecontrolapp.presenter.count.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.model.count.CountModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.count.IUserMaxEventCountPersenter;

import java.util.Map;

/**
 * Created by nd on 2018-07-16.
 */

class UserMaxEventCountPersenterImpl  extends BasePersenter implements IUserMaxEventCountPersenter{

    private IUserMaxEventCountPersenter.IUserMaxEventCountView view;
    private IUserMaxEventCountPersenter.IUserMaxEventCountModel model;

    public UserMaxEventCountPersenterImpl(IUserMaxEventCountPersenter.IUserMaxEventCountView view){
        this.view = view;
        this.model = CountModelFactory.getUserMaxEventCountModel(this);
    }

    @Override
    public void callBackError(ResultBean<String, String> result, String action) {
        if(view!=null){
            view.callBackError(result,action);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void doUserMaxEventCount() {
        model.requestUserMaxEventCount();
    }

    @Override
    public void callBackUserMaxEventCount(Map<String, String> result) {
        if(view!=null){
            view.callBackUserMaxEventCount(result);
        }

    }
}
