package com.ynzhxf.nd.firecontrolapp.model.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.ProjectNodeBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IProjectInfoPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 货期项目信息
 * Created by nd on 2018-07-19.
 */

class ProjectInfoModel extends BaseModel implements IProjectInfoPersenter.IProjectInfoModel {

    private IProjectInfoPersenter persenter;

    public ProjectInfoModel(IProjectInfoPersenter persenter){
        this.persenter = persenter;
    }
    @Override
    public void requestProjectInfo(String proID) {
        HttpUtils.getRetrofit().getProjectInfo(HelperTool.getToken(),proID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<ProjectNodeBean,String>>() {
                               @Override
                               public void onNext(ResultBean<ProjectNodeBean,String> resultBean) {
                                   persenter.callBackProjectInfo(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IProjectInfoPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
