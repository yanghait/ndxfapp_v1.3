package com.ynzhxf.nd.firecontrolapp.presenter.message.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.model.message.MessageModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.message.IUserMsgLogNotSeeCountPersenter;

/**
 * 获取用户还未查看的消息数量
 * Created by nd on 2018-08-02.
 */

class UserMsgLogNotSeeCountPersenterImpl extends BasePersenter implements IUserMsgLogNotSeeCountPersenter {

    private IUserMsgLogNotSeeCountView view;

    private IUserMsgLogNotSeeCountModel model;

    public UserMsgLogNotSeeCountPersenterImpl(IUserMsgLogNotSeeCountView view){
        this.view = view;
        this.model = MessageModelFactory.getUserMsgLogNotSeeCountModel(this);
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
    public void doUserUserMsgLogNotSeeCount() {
        model.requestUserMsgLogNotSeeCount();
    }

    @Override
    public void callBackUserMsgLogNotSeeCount(ResultBean<Integer, String> result) {
        if(view != null){
            view.callBackUserMsgLogNotSeeCount(result);
        }
    }
}
