package com.ynzhxf.nd.firecontrolapp.presenter.share.impl;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareMyFileBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareMyFileInputBean;
import com.ynzhxf.nd.firecontrolapp.model.share.FileShareFactory;
import com.ynzhxf.nd.firecontrolapp.presenter.BasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.share.IFileShareMyFileListPresenter;

import java.util.List;

public class FileShareMyFileListImpl extends BasePersenter implements IFileShareMyFileListPresenter {

    private IFileShareMyFileListPresenter.IFileShareMyFileListModel model;
    private IFileShareMyFileListPresenter.IFileShareMyFileListView view;

    public FileShareMyFileListImpl(IFileShareMyFileListPresenter.IFileShareMyFileListView view) {
        this.view = view;
        model = FileShareFactory.getFileShareMyFile(this);
    }

    @Override
    public void doFileShareMyFileList(FileShareMyFileInputBean bean) {
        model.requestFileShareMyFileList(bean);
    }

    @Override
    public void callBackFileShareMyFileList(ResultBean<List<FileShareMyFileBean>, String> resultBean) {
        if (view != null) {
            view.callBackFileShareMyFileList(resultBean);
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
