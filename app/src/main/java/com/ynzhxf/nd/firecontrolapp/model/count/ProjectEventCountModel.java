package com.ynzhxf.nd.firecontrolapp.model.count;


import com.ynzhxf.nd.firecontrolapp.GloblePlantformDatas;
import com.ynzhxf.nd.firecontrolapp.base.PreferencesService;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.count.IProjectEventCountPersenter;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目事件统计和实时报警数量统计
 * Created by nd on 2018-07-16.
 */

class ProjectEventCountModel extends BaseModel implements IProjectEventCountPersenter.IProjectEventCountModel {
    private IProjectEventCountPersenter persenter;

    public ProjectEventCountModel(IProjectEventCountPersenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void requestProjectEventCount(String proID) {
        HttpUtils.getRetrofit().getProjectEventCount(new PreferencesService().getToken(), proID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Map<String, String>>() {
                               @Override
                               public void onNext(Map<String, String> result) {
                                   persenter.callBackProjectEventCount(result);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e), IProjectEventCountPersenter.TAG);
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }

}
