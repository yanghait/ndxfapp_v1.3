package com.ynzhxf.nd.firecontrolapp.model.share;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareFileTypeBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareFileTypeInputBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.share.IFileShareFileTypePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FileShareFileTypeModel extends BaseModel implements IFileShareFileTypePresenter.IFileShareFileTypeModel {

    private IFileShareFileTypePresenter presenter;

    public FileShareFileTypeModel(IFileShareFileTypePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestFileShareFileType(FileShareFileTypeInputBean bean) {
        HttpUtils.getRetrofit().getFileShareFileType(bean.getToken(), bean.getProjectId(), bean.getKeyword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<List<FileShareFileTypeBean>, String>>() {
                    @Override
                    public void onNext(ResultBean<List<FileShareFileTypeBean>, String> resultBean) {
                        presenter.callBackFileShareFileType(resultBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.callBackError(createResult(e), IFileShareFileTypePresenter.TAG);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
