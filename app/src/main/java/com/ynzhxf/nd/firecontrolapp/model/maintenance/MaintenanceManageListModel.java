package com.ynzhxf.nd.firecontrolapp.model.maintenance;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.MaintenListAllBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.MaintenanceListInfoBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.maintenance.IMaintenanceListPresenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MaintenanceManageListModel extends BaseModel implements IMaintenanceListPresenter.IMaintenanceListModel {

    private IMaintenanceListPresenter presenter;

    public MaintenanceManageListModel(IMaintenanceListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestgetMaintenList(final MaintenanceListInfoBean bean) {
        HttpUtils.getRetrofit().getOwnerMainList(HelperTool.getToken(), bean.getProjectId(), bean.getState(), bean.getPageIndex(), bean.getPageSize(), bean.getStartTime(), bean.getEndTime(), bean.getIsWorking())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<MaintenListAllBean, String>>() {
                    @Override
                    public void onNext(ResultBean<MaintenListAllBean, String> Bean) {
                        presenter.callBackMaintenanceList(Bean);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        presenter.callBackError(createResult(throwable), IMaintenanceListPresenter.TAG);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
