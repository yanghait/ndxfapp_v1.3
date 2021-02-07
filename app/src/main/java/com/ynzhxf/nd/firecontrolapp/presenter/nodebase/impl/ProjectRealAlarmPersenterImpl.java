package com.ynzhxf.nd.firecontrolapp.presenter.nodebase.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.LabelNodeBean;
import com.ynzhxf.nd.firecontrolapp.model.nodebase.NodeBaseModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IProjectRealAlarmPersenter;

import java.util.List;

/**
 * Created by nd on 2018-07-26.
 */

 class ProjectRealAlarmPersenterImpl extends BasePersenter implements IProjectRealAlarmPersenter{

    private IProjectRealAlarmView view;

    private IProjectRealAlarmModel model;

    public ProjectRealAlarmPersenterImpl(IProjectRealAlarmView view){
        this.view = view;
        this.model = NodeBaseModelFactory.getProjectRealAlarmModel(this);

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
    public void doProjectRealAlarm(String proID) {
        model.requestProjectRealAlarmList(proID);
    }

    @Override
    public void callBackProjectRealAlarm(ResultBean<List<LabelNodeBean>, String> result) {
        if(view != null){
            view.callBackProjectRealAlarm(result);
        }
    }
}
