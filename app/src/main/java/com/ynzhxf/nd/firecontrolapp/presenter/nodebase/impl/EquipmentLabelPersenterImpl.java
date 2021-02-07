package com.ynzhxf.nd.firecontrolapp.presenter.nodebase.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.TreeGridBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.LabelNodeBean;
import com.ynzhxf.nd.firecontrolapp.model.nodebase.NodeBaseModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IEquipmentLabelPersenter;

import java.util.List;

/**
 * Created by nd on 2018-07-23.
 */

public class EquipmentLabelPersenterImpl extends BasePersenter implements IEquipmentLabelPersenter {

    private IEquipmentLabelView view;

    private IEquipmentLabelModel model;

    public EquipmentLabelPersenterImpl(IEquipmentLabelView view){
        this.view = view;
        this.model = NodeBaseModelFactory.getEquipmentLabelModel(this);
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
    public void doRequestEquipmentLabel(String proSysID) {
        model.requestEquipmentLabel(proSysID);
    }

    @Override
    public void callBackProjectSettingRepaireOrToken(ResultBean<List<TreeGridBean<LabelNodeBean>>,String> result) {
        if(view != null){
            view.callBackEquipmentLabel(result);
        }
    }
}
