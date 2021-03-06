package com.ynzhxf.nd.firecontrolapp.model.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.firealarm.FireAlarmHostBuildInfoCountBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IFireAlarmBuildListPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 获取火灾报警主机下的建筑物列表
 * Created by nd on 2018-07-26.
 */

class FireAlarmBuildListModel extends BaseModel implements IFireAlarmBuildListPersenter.IFireAlarmBuildListModel{

    private IFireAlarmBuildListPersenter persenter;

    public FireAlarmBuildListModel(IFireAlarmBuildListPersenter persenter){
        this.persenter = persenter;
    }

    @Override
    public void requestFireAlarmBuildList(String proSysID, String hostID) {
        HttpUtils.getRetrofit().getFireAalarmHostBuildList(HelperTool.getToken() , proSysID , hostID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<FireAlarmHostBuildInfoCountBean,String>>() {
                               @Override
                               public void onNext(ResultBean<FireAlarmHostBuildInfoCountBean,String> resultBean) {
                                   persenter.callBackFireAlarmBuildList(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IFireAlarmBuildListPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
