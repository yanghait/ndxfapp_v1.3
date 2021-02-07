package com.ynzhxf.nd.firecontrolapp.presenter.maintenance;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerImpowerInputBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerImpowerListBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

import java.util.List;

public interface IMainOwnerImpowerListPresenter extends IBasePersenter {

    String TAG = "IMainOwnerImpowerListPresenter";

    interface IMainOwnerImpowerListView extends IBaseView {
        /**
         * 维保创建工单获取授权列表回调
         *
         * @param
         */
        void callBackMainOwnerImpowerList(ResultBean<List<OwnerImpowerListBean>, String> resultBean);

    }

    interface IMainOwnerImpowerListModel {
        /**
         * 请求业主维保授权列表
         *
         * @param bean
         */
        void requestgetMainOwnerImpowerList(OwnerImpowerInputBean bean);
    }

    /**
     * 业主维保授权列表
     *
     * @param bean
     */
    void doMainOwnerImpowerList(OwnerImpowerInputBean bean);

    /**
     * 获取业主维保授权列表回调
     *
     * @param
     */
    void callBackMainOwnerImpowerList(ResultBean<List<OwnerImpowerListBean>, String> resultBean);
}
