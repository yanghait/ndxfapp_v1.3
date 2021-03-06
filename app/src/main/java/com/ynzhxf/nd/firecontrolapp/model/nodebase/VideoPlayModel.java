package com.ynzhxf.nd.firecontrolapp.model.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IVideoPlayPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目视频列表获取
 * Created by nd on 2018-07-21.
 */

class VideoPlayModel extends BaseModel implements IVideoPlayPersenter.IVideoPlayModel {

    private IVideoPlayPersenter persenter;

    public VideoPlayModel(IVideoPlayPersenter persenter){
        this.persenter = persenter;
    }

    @Override
    public void requestVideoPlay(String channelID) {
        HttpUtils.getRetrofit().getVideoPlayRtmp(HelperTool.getToken(),channelID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<String,String>>() {
                               @Override
                               public void onNext(ResultBean<String,String> result) {
                                   persenter.callBackPVideoPlay(result);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IVideoPlayPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
