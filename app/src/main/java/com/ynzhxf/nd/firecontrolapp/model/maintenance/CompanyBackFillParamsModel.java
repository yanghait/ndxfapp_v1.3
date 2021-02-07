package com.ynzhxf.nd.firecontrolapp.model.maintenance;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.CompanyBackFillParamsBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerOrderDetailsInputBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.maintenance.ICompanyBackFillParamsPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CompanyBackFillParamsModel extends BaseModel implements ICompanyBackFillParamsPresenter.ICompanyBackFillParamsModel {

    private ICompanyBackFillParamsPresenter presenter;

    public CompanyBackFillParamsModel(ICompanyBackFillParamsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestCompanyBackFillParams(OwnerOrderDetailsInputBean bean) {
        HttpUtils.getRetrofit().getCompanyBackFillParams(bean.getToken(), bean.getWorkOrderId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<CompanyBackFillParamsBean, String>>() {
                    @Override
                    public void onNext(ResultBean<CompanyBackFillParamsBean, String> resultBean) {
                        presenter.callBackCompanyBackFillParams(resultBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.callBackError(createResult(e), ICompanyBackFillParamsPresenter.TAG);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
