package com.ynzhxf.nd.firecontrolapp.presenter.platform;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;


/**
 * 用户信息获取
 * Created by nd on 2018-07-13.
 */

public interface IUserInfoGetPersenter extends IBasePersenter{

    String TAG = "UserInfoGet";

     interface IUserInfoGetView extends IBaseView{
        /**
         * 获取用户信息回调
         * @param resultBean
         */
        void callBackLoginKeyGet(ResultBean<LoginInfoBean, String> resultBean);

    }

     interface IUserInfoGetModel{
        /**
         * 数据获取信息请求
         */
        void requestUserInfoGet();
    }

    /**
     * 数据获取
     */
    void dolUserInfoGetPersenter();

    /**
     * 获取成功回调
     * @param result
     */
    void callBackUserInfoGet(ResultBean<LoginInfoBean, String> result);




}
