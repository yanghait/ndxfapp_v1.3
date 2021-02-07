package com.ynzhxf.nd.firecontrolapp.presenter.maintenance.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerOrderDetailsInputBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerWorkOrderDetailsBean;
import com.ynzhxf.nd.firecontrolapp.model.maintenance.MaintenanceManageFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.maintenance.IOwnerOrderDetailsPresenter;

public class OwnerOrderDetailsImpl extends BasePersenter implements IOwnerOrderDetailsPresenter {

    private IOwnerOrderDetailsPresenter.IOwnerOrderDetailsModel model;
    private IOwnerOrderDetailsPresenter.IOwnerOrderDetailsView view;

    public OwnerOrderDetailsImpl(IOwnerOrderDetailsPresenter.IOwnerOrderDetailsView view) {
        this.view = view;
        this.model = MaintenanceManageFactory.getMainOwnerOrderDetails(this);
    }

    @Override
    public void doOwnerOrderDetails(OwnerOrderDetailsInputBean bean) {
        model.requestOwnerOrderDetails(bean);
    }

    @Override
    public void callBackOwnerOrderDetails(ResultBean<OwnerWorkOrderDetailsBean, String> resultBean) {
        if (view != null) {
            view.callBackOwnerOrderDetails(resultBean);
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
