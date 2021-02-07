package com.ynzhxf.nd.firecontrolapp.model.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.VideoChannelBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IProjectVideoPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目视频列表获取
 * Created by nd on 2018-07-21.
 */

class ProjectVideoModel extends BaseModel implements IProjectVideoPersenter.IProjectVideoModel {

    private IProjectVideoPersenter persenter;

    public  ProjectVideoModel(IProjectVideoPersenter persenter){
        this.persenter = persenter;
    }
    @Override
    public void requestProjectVideo(String projectID) {
        HttpUtils.getRetrofit().getProjectVideoListByprojectID(HelperTool.getToken(),projectID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<List<VideoChannelBean>,String>>() {
                               @Override
                               public void onNext(ResultBean<List<VideoChannelBean>,String> resultBean) {
                                   persenter.callBackProjectVideo(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IProjectVideoPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
