package com.ynzhxf.nd.firecontrolapp.presenter.maintenance;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.CompanyBackFillParamsBean;
import com.ynzhxf.nd.firecontrolapp.bean.maintenance.OwnerOrderDetailsInputBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

public interface ICompanyBackFillParamsPresenter extends IBasePersenter {

    String TAG = "ICompanyBackFillParamsPresenter";

    interface ICompanyBackFillParamsView extends IBaseView {
        /**
         * 公司维保回填参数回调
         *
         * @param
         */
        void callBackCompanyBackFillParams(ResultBean<CompanyBackFillParamsBean, String> resultBean);

    }

    interface ICompanyBackFillParamsModel {
        /**
         * 请求公司维保回填参数
         *
         * @param bean
         */
        void requestCompanyBackFillParams(OwnerOrderDetailsInputBean bean);
    }

    /**
     * 公司维保回填参数
     *
     * @param bean
     */
    void doCompanyBackFillParams(OwnerOrderDetailsInputBean bean);

    /**
     * 公司维保回填参数回调
     *
     * @param
     */
    void callBackCompanyBackFillParams(ResultBean<CompanyBackFillParamsBean, String> resultBean);
}
