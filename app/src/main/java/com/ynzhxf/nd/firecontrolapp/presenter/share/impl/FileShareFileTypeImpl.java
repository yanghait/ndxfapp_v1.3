package com.ynzhxf.nd.firecontrolapp.presenter.share.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareFileTypeBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareFileTypeInputBean;
import com.ynzhxf.nd.firecontrolapp.model.share.FileShareFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.share.IFileShareFileTypePresenter;

import java.util.List;

public class FileShareFileTypeImpl extends BasePersenter implements IFileShareFileTypePresenter {

    private IFileShareFileTypePresenter.IFileShareFileTypeModel model;
    private IFileShareFileTypePresenter.IFileShareFileTypeView view;

    public FileShareFileTypeImpl(IFileShareFileTypePresenter.IFileShareFileTypeView view) {
        this.view = view;
        model = FileShareFactory.getFileShareFileType(this);
    }

    @Override
    public void doFileShareFileType(FileShareFileTypeInputBean bean) {
        model.requestFileShareFileType(bean);
    }

    @Override
    public void callBackFileShareFileType(ResultBean<List<FileShareFileTypeBean>, String> resultBean) {
        if (view != null) {
            view.callBackFileShareFileType(resultBean);
        }
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
}
