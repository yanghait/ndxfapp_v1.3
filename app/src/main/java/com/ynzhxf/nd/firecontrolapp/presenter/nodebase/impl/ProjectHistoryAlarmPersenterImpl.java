package com.ynzhxf.nd.firecontrolapp.presenter.nodebase.impl;

import com.ynzhxf.nd.firecontrolapp.bean.PagingBean;
import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.AlarmLogBean;
import com.ynzhxf.nd.firecontrolapp.model.nodebase.NodeBaseModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IProjectHistoryAlarmPersenter;

/**
 * 项目历史报警记录获取
 * Created by nd on 2018-07-27.
 */

class ProjectHistoryAlarmPersenterImpl extends BasePersenter implements IProjectHistoryAlarmPersenter {

    private IProjectHistoryAlarmView view;

    private IProjectHistoryAlarmModel model;

    public ProjectHistoryAlarmPersenterImpl(IProjectHistoryAlarmView view){
        this.view = view;
        this.model = NodeBaseModelFactory.getProjectHistoryAlarmModel(this);
    }
    @Override
    public void callBackError(ResultBean<String, String> result, String action) {
        if(view !=null){
            view.callBackError(result , action);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void doProjectHistoryAlarm(int pageSize, int count, String proID,String startTime,String endTime) {
        model.requestProjectHistoryAlarm(pageSize, count, proID,startTime,endTime);
    }

    @Override
    public void callBackProjectInfo(ResultBean<PagingBean<AlarmLogBean>, String> result) {
        if(view != null){
            view.callBackProjectHistoryAlarm(result);
        }
    }
}
