package com.ynzhxf.nd.firecontrolapp.presenter.platform;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;


/**
 * 用户密码修改
 * Created by nd on 2018-07-13.
 */

public interface IUserPwdChangePersenter extends IBasePersenter{
    String TAG = "UserPwdChange";
     interface IUserPwdChangeView extends IBaseView{
        /**
         * 请求完成回调
         * @param resultBean
         */
        void callBackUserPwdChange(ResultBean<String, String> resultBean);

    }

     interface IUserPwdChangeModel{


         /**\
          * 数据获取信息请求
          * @param oldPwd 原密码
          * @param newPwd 新密码
          */
        void requestUserPwdChange(String oldPwd , String newPwd);
    }

    /**
     * 密码修改请求
     * @param oldPwd
     * @param newPwd
     */
    void dolUserPwdChangePersenter(String oldPwd , String newPwd);

    /**
     * 获取成功回调
     * @param result
     */
    void callBackUserPwdChange(ResultBean<String, String> result);




}
