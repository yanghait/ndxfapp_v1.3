package com.ynzhxf.nd.firecontrolapp.presenter.maintenance.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.CompanyBackFillParamsBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerOrderDetailsInputBean;
import com.ynzhxf.nd.firecontrolapp.model.maintenance.MaintenanceManageFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.maintenance.ICompanyBackFillParamsPresenter;

public class CompanyBackFillParamsImpl extends BasePersenter implements ICompanyBackFillParamsPresenter {

    private ICompanyBackFillParamsPresenter.ICompanyBackFillParamsModel model;

    private ICompanyBackFillParamsPresenter.ICompanyBackFillParamsView view;

    public CompanyBackFillParamsImpl(ICompanyBackFillParamsPresenter.ICompanyBackFillParamsView view) {
        this.view = view;
        model = MaintenanceManageFactory.getCompamyBackFillParams(this);
    }

    @Override
    public void doCompanyBackFillParams(OwnerOrderDetailsInputBean bean) {
        model.requestCompanyBackFillParams(bean);
    }

    @Override
    public void callBackCompanyBackFillParams(ResultBean<CompanyBackFillParamsBean, String> resultBean) {
        if (view != null) {
            view.callBackCompanyBackFillParams(resultBean);
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
