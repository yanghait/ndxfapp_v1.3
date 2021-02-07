package com.ynzhxf.nd.firecontrolapp.model.message;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.message.IUserMsgLogSetAllSeePersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nd on 2018-08-02.
 */

class UserMsgLogSetAllSeeModel extends BaseModel implements IUserMsgLogSetAllSeePersenter.IUserMsgLogSetAllSeeModel {

    private IUserMsgLogSetAllSeePersenter persenter;

    public UserMsgLogSetAllSeeModel(IUserMsgLogSetAllSeePersenter persenter){
        this.persenter = persenter;
    }

    @Override
    public void requestUserMsgLogSetAllSee() {
        HttpUtils.getRetrofit().settingUserMsgLogSetAllSee(HelperTool.getToken() )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<Boolean,String>>() {
                               @Override
                               public void onNext(ResultBean<Boolean ,String>  resultBean) {
                                   persenter.callBackUserMsgLogSetAllSee(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IUserMsgLogSetAllSeePersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
