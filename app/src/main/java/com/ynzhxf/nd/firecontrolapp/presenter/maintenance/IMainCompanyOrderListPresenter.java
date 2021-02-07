package com.ynzhxf.nd.firecontrolapp.presenter.maintenance;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.CompanyOrderListInputBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.MaintenListAllBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

public interface IMainCompanyOrderListPresenter extends IBasePersenter {

    String TAG = "IMainCompanyOrderListPresenter";

    interface ICompanyOrderListView extends IBaseView {
        /**
         * 维保公司获取工单回调
         *
         * @param
         */
        void callBackCompanyOrderList(ResultBean<MaintenListAllBean, String> resultBean);

    }

    interface ICompanyOrderListModel {
        /**
         * 请求维保公司工单
         *
         * @param bean
         */
        void requestCompanyOrderList(CompanyOrderListInputBean bean);
    }

    /**
     * 维保公司工单
     *
     * @param bean
     */
    void doCompanyOrderList(CompanyOrderListInputBean bean);

    /**
     * 获取维保公司工单
     *
     * @param
     */
    void callBackCompanyOrderList(ResultBean<MaintenListAllBean, String> resultBean);
}
