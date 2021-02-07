package com.ynzhxf.nd.firecontrolapp.model.Platform;

import com.ynzhxf.nd.firecontrolapp.GloblePlantformDatas;
import com.ynzhxf.nd.firecontrolapp.base.PreferencesService;
import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.platform.IUserInfoGetPersenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 获取用户相关信息
 * Created by nd on 2018-07-31.
 */

class UserInfoGetModel extends BaseModel implements IUserInfoGetPersenter.IUserInfoGetModel {

    private IUserInfoGetPersenter persenter;

    public UserInfoGetModel(IUserInfoGetPersenter persenter){
        this.persenter = persenter;
    }

    @Override
    public void requestUserInfoGet() {
        HttpUtils.getRetrofit().userInfoGet(new PreferencesService().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<LoginInfoBean,String>>() {
                               @Override
                               public void onNext(ResultBean<LoginInfoBean,String> bean) {
                                   persenter.callBackUserInfoGet(bean);
                               }
                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IUserInfoGetPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
