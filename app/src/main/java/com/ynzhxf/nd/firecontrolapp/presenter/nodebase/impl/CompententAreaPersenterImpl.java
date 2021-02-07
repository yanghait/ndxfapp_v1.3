package com.ynzhxf.nd.firecontrolapp.presenter.nodebase.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.ProjectNodeBean;
import com.ynzhxf.nd.firecontrolapp.model.nodebase.NodeBaseModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.ICompententAreaPersenter;

import java.util.List;

/**
 * 主管部门区域数据交换
 * Created by nd on 2018-07-17.
 */

class CompententAreaPersenterImpl extends BasePersenter implements ICompententAreaPersenter {

    private ICompententAreaModel model;

    private ICompententAreaView view ;

    public CompententAreaPersenterImpl(ICompententAreaView view){
        this.view = view;
        this.model = NodeBaseModelFactory.getICompententAreaModel(this);
    }

    @Override
    public void callBackError(ResultBean<String, String> result, String action) {
        if(view != null){
            view.callBackError(result,action);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void doCompententArea() {
        model.requestCompententArea();
    }

    @Override
    public void callBackCompententArea(ResultBean<List<ProjectNodeBean>,String[]> result) {
        if(view != null){
            view.callBackCompententArea(result);
        }
    }
}
