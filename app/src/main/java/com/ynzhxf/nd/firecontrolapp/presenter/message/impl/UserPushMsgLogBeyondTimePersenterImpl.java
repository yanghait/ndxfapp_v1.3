package com.ynzhxf.nd.firecontrolapp.presenter.message.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.message.UserMessagePushLogBean;
import com.ynzhxf.nd.firecontrolapp.model.message.MessageModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.message.IUserPushMsgLogBeyondTimePersenter;

import java.util.List;

/**
 * Created by nd on 2018-08-02.
 */

class UserPushMsgLogBeyondTimePersenterImpl extends BasePersenter implements IUserPushMsgLogBeyondTimePersenter {

    private IUserPushMsgLogBeyondTimeView view;
    private IUserPushMsgLogBeyondTimeModel model;

    public UserPushMsgLogBeyondTimePersenterImpl(IUserPushMsgLogBeyondTimeView view) {
        this.view = view;
        this.model = MessageModelFactory.getUserPushMsgLogBeyondTimeModel(this);
    }

    @Override
    public void callBackError(ResultBean<String, String> result, String action) {
        if (view != null) {
            view.callBackError(result, action);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void doUserPushMsgLogBeyondTime(String pushLogID, String typeId) {
        model.requestUserPushMsgLog(pushLogID, typeId);
    }

    @Override
    public void callBackUserPushMsgLogBeyondTime(ResultBean<List<UserMessagePushLogBean>, String> result) {
        if (view != null) {
            view.callBackUserPushMsgLogBeyondTime(result);
        }
    }
}
