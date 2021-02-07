package com.ynzhxf.nd.firecontrolapp.presenter.share;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareFileTypeBean;
import com.ynzhxf.nd.firecontrolapp.bean.share.FileShareFileTypeInputBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

import java.util.List;

public interface IFileShareFileTypePresenter extends IBasePersenter {

    String TAG = "IFileShareFileTypePresenter";

    interface IFileShareFileTypeView extends IBaseView {
        /**
         * 文件分享文件类型回调
         *
         * @param
         */
        void callBackFileShareFileType(ResultBean<List<FileShareFileTypeBean>, String> resultBean);

    }

    interface IFileShareFileTypeModel {
        /**
         * 请求文件分享文件类型
         *
         * @param bean
         */
        void requestFileShareFileType(FileShareFileTypeInputBean bean);
    }

    /**
     * 文件分享文件类型
     *
     * @param bean
     */
    void doFileShareFileType(FileShareFileTypeInputBean bean);

    /**
     * 获取文件分享文件类型回调
     *
     * @param
     */
    void callBackFileShareFileType(ResultBean<List<FileShareFileTypeBean>, String> resultBean);
}
