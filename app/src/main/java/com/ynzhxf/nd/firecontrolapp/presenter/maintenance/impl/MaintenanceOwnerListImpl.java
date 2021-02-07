package com.ynzhxf.nd.firecontrolapp.presenter.maintenance.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.MaintenListAllBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.MaintenanceListInfoBean;
import com.ynzhxf.nd.firecontrolapp.model.maintenance.MaintenanceManageFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.maintenance.IMaintenanceListPresenter;

public class MaintenanceOwnerListImpl extends BasePersenter implements IMaintenanceListPresenter {
    private IMaintenanceListModel model;
    private IMaintenanceListView view;

    public MaintenanceOwnerListImpl(IMaintenanceListView view) {
        this.view = view;
        model = MaintenanceManageFactory.getMainOwnerWorkList(this);
    }

    @Override
    public void doMaintenanceList(MaintenanceListInfoBean bean) {
        model.requestgetMaintenList(bean);
    }

    @Override
    public void callBackMaintenanceList(ResultBean<MaintenListAllBean, String> resultBean) {
        if (view != null) {
            view.callBackMaintenanceList(resultBean);
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
