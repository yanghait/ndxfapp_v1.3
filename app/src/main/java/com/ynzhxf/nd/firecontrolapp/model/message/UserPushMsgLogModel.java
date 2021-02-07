package com.ynzhxf.nd.firecontrolapp.model.message;

import com.ynzhxf.nd.firecontrolapp.bean.PagingBean;
import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.message.UserMessagePushLogBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.message.IUserPushMsgLogPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 获取用户消息分页
 * Created by nd on 2018-08-02.
 */

class UserPushMsgLogModel extends BaseModel implements IUserPushMsgLogPersenter.IUserPushMsgLogModel {

    private IUserPushMsgLogPersenter persenter;

    public UserPushMsgLogModel(IUserPushMsgLogPersenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void requestUserPushMsgLog(int pageSize, String typeId) {
        HttpUtils.getRetrofit().getUserMessageList(HelperTool.getToken(), pageSize, typeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<PagingBean<UserMessagePushLogBean>, String>>() {
                               @Override
                               public void onNext(ResultBean<PagingBean<UserMessagePushLogBean>, String> resultBean) {
                                   persenter.callBackUserPushMsgLog(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e), IUserPushMsgLogPersenter.TAG);
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
