package com.ynzhxf.nd.firecontrolapp.presenter.message.impl;

import com.ynzhxf.nd.firecontrolapp.bean.PagingBean;
import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.message.UserMessagePushLogBean;
import com.ynzhxf.nd.firecontrolapp.model.message.MessageModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.message.IUserPushMsgLogPersenter;

/**
 * 分页获取用户消息记录
 * Created by nd on 2018-08-02.
 */

class UserPushMsgLogPersenterImpl extends BasePersenter implements IUserPushMsgLogPersenter {

    private IUserPushMsgLogView view;

    private  IUserPushMsgLogModel model;

    public UserPushMsgLogPersenterImpl(IUserPushMsgLogView view){
        this.view = view;
        this.model = MessageModelFactory.getUserPushMsgLogModel(this);
    }

    @Override
    public void callBackError(ResultBean<String, String> result, String action) {
        if(view != null){
            view.callBackError(result , action);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void doUserPushMsgLog(int pageSize,String typeId) {
        model.requestUserPushMsgLog(pageSize,typeId);
    }

    @Override
    public void callBackUserPushMsgLog(ResultBean<PagingBean<UserMessagePushLogBean>, String> result) {
        if(view != null){
            view.callBackUserPushMsgLog(result);
        }
    }
}
