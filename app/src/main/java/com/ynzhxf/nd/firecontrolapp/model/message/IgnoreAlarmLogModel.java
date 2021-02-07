package com.ynzhxf.nd.firecontrolapp.model.message;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.AlarmLogBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.message.IIgnoreAlarmLogPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nd on 2018-08-02.
 */

class IgnoreAlarmLogModel extends BaseModel implements IIgnoreAlarmLogPersenter.IIgnoreAlarmLogModel {

    private IIgnoreAlarmLogPersenter persenter;

    public IgnoreAlarmLogModel(IIgnoreAlarmLogPersenter persenter){
        this.persenter = persenter;
    }

    @Override
    public void requestIgnoreAlarmLog(String ID) {
        HttpUtils.getRetrofit().setIgnoreAlarmLog(HelperTool.getToken() , ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<AlarmLogBean,String>>() {
                               @Override
                               public void onNext(ResultBean<AlarmLogBean ,String>  resultBean) {
                                   persenter.callBackIgnoreAlarmLog(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IIgnoreAlarmLogPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
