package com.ynzhxf.nd.firecontrolapp.model.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.AlarmLogBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IAlarmLogInfoPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nd on 2018-08-02.
 */

class AlarmLogInfoModel extends BaseModel implements IAlarmLogInfoPersenter.IAlarmLogInfoModel {

    private IAlarmLogInfoPersenter perserter;

    public AlarmLogInfoModel(IAlarmLogInfoPersenter perserter){
        this.perserter = perserter;
    }
    @Override
    public void requestAlarmLogInfo(String ID) {
        HttpUtils.getRetrofit().getAlarmLogByAlarmID(HelperTool.getToken() , ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<AlarmLogBean,String>>() {
                               @Override
                               public void onNext(ResultBean<AlarmLogBean,String> resultBean) {
                                   perserter.callBackAlarmLogInfo(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   perserter.callBackError(createResult(e), IAlarmLogInfoPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
