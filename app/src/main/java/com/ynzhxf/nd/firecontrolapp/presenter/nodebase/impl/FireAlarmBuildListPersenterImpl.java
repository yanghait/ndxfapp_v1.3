package com.ynzhxf.nd.firecontrolapp.presenter.nodebase.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.firealarm.FireAlarmHostBuildInfoCountBean;
import com.ynzhxf.nd.firecontrolapp.model.nodebase.NodeBaseModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IFireAlarmBuildListPersenter;

/**
 * 获取火灾报警主机列表
 * Created by nd on 2018-07-25.
 */

class FireAlarmBuildListPersenterImpl extends BasePersenter implements IFireAlarmBuildListPersenter {

    private IFireAlarmBuildListView view;
    private IFireAlarmBuildListModel model;

    public FireAlarmBuildListPersenterImpl(IFireAlarmBuildListView view){
        this.view = view;
        this.model = NodeBaseModelFactory.getFireAlarmHostBuildListModel(this);

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
    public void doFireAlarmBuildList(String proSysID, String hostID) {
        model.requestFireAlarmBuildList(proSysID , hostID);
    }

    @Override
    public void callBackFireAlarmBuildList(ResultBean<FireAlarmHostBuildInfoCountBean, String> result) {
        if(view !=null){
            view.callBackFireAlarmBuildList(result);
        }
    }
}
