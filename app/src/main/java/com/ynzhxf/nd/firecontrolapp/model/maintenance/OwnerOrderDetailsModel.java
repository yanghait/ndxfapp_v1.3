package com.ynzhxf.nd.firecontrolapp.model.maintenance;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerOrderDetailsInputBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerWorkOrderDetailsBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.maintenance.IOwnerOrderDetailsPresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class OwnerOrderDetailsModel extends BaseModel implements IOwnerOrderDetailsPresenter.IOwnerOrderDetailsModel {

    private IOwnerOrderDetailsPresenter presenter;

    public OwnerOrderDetailsModel(IOwnerOrderDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestOwnerOrderDetails(OwnerOrderDetailsInputBean bean) {
        Observable<ResultBean<OwnerWorkOrderDetailsBean, String>> observable;
        if (bean.getDetailType() == 2) {
            observable = HttpUtils.getRetrofit().getCompanyOrderDetails(bean.getToken(), bean.getWorkOrderId());
        } else if (bean.getDetailType() == 3) {
            observable = HttpUtils.getRetrofit().getChargeOrderDetails(bean.getToken(), bean.getWorkOrderId());
        } else {
            observable = HttpUtils.getRetrofit().getOwnerOrderDetails(bean.getToken(), bean.getWorkOrderId());
        }
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<OwnerWorkOrderDetailsBean, String>>() {
                    @Override
                    public void onNext(ResultBean<OwnerWorkOrderDetailsBean, String> resultBean) {
                        presenter.callBackOwnerOrderDetails(resultBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.callBackError(createResult(e), IOwnerOrderDetailsPresenter.TAG);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
