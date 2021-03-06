package com.ynzhxf.nd.firecontrolapp.presenter.nodebase.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.VideoChannelBean;
import com.ynzhxf.nd.firecontrolapp.model.nodebase.NodeBaseModelFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IProjectVideoPersenter;

import java.util.List;

/**
 *
 * Created by nd on 2018-07-21.
 */

class ProjectVideoPersenterImpl extends BasePersenter implements IProjectVideoPersenter {

    private IProjectVideoView view;

    private IProjectVideoModel model;

    public ProjectVideoPersenterImpl(IProjectVideoView view){
        this.view = view;
        this.model = NodeBaseModelFactory.getProjectVideoModelImpl(this);
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
    public void doProjectVideo(String projectID) {
        model.requestProjectVideo(projectID);
    }

    @Override
    public void callBackProjectVideo(ResultBean<List<VideoChannelBean>, String> result) {
        if(view != null){
            view.callBackProjectVideo(result);
        }
    }
}
