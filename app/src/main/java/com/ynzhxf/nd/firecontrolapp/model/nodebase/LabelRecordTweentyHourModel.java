package com.ynzhxf.nd.firecontrolapp.model.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.RealDataLogBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.ILabelRecordTweentyHourPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 标签写值
 * Created by nd on 2018-07-24.
 */

class LabelRecordTweentyHourModel extends BaseModel implements ILabelRecordTweentyHourPersenter.ILabelRecordTweentyHourModel {

    private ILabelRecordTweentyHourPersenter persenter;

    public LabelRecordTweentyHourModel(ILabelRecordTweentyHourPersenter persenter) {
        this.persenter = persenter;
    }


    @Override
    public void requestLabelWriteValue(String labelID, String startTime, String endTime) {
        HttpUtils.getRetrofit().getLabelTwentyFourHoursDataLog(HelperTool.getToken(), labelID, startTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<List<RealDataLogBean>, String>>() {
                               @Override
                               public void onNext(ResultBean<List<RealDataLogBean>, String> resultBean) {
                                   persenter.callBackLabelRecordTweentyHour(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e), ILabelRecordTweentyHourPersenter.TAG);
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
