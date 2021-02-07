package com.ynzhxf.nd.firecontrolapp.presenter.maintenance.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerImpowerInputBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerImpowerListBean;
import com.ynzhxf.nd.firecontrolapp.model.maintenance.MaintenanceManageFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.maintenance.IMainOwnerImpowerListPresenter;

import java.util.List;

public class MainOwnerImpowerListImpl extends BasePersenter implements IMainOwnerImpowerListPresenter {
    private IMainOwnerImpowerListModel model;
    private IMainOwnerImpowerListView view;

    public MainOwnerImpowerListImpl(IMainOwnerImpowerListView view) {
        this.view = view;
        model = MaintenanceManageFactory.getMainOwnerImpowerList(this);
    }

    @Override
    public void doMainOwnerImpowerList(OwnerImpowerInputBean bean) {
        model.requestgetMainOwnerImpowerList(bean);
    }

    @Override
    public void callBackMainOwnerImpowerList(ResultBean<List<OwnerImpowerListBean>, String> resultBean) {
        if (view != null) {
            view.callBackMainOwnerImpowerList(resultBean);
        }
    }

    @Override
    public void callBackError(ResultBean<String, String> result, String action) {
        if (view != null) {
            view.callBackError(result, action);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }
}
