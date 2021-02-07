package com.ynzhxf.nd.firecontrolapp.presenter.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

/**
 *
 * 对标签尽心写值操作
 * Created by nd on 2018-07-19.
 */

public interface ILabelWriteValuePersenter extends IBasePersenter{

    String TAG = "LabelWriteValue";

    /**
     * 视图接口
     */
    interface ILabelWriteValueView extends IBaseView {
        /**
         *数据请求完成回调
         */
        void callBackLabelWriteValue(ResultBean<String, String> result);

    }

    /**
     * 模型接口
     */
    interface ILabelWriteValueModel{

        /**
         * 数据请求
         * @param labelID 标签ID
         * @param confirmPwd 确认密码
         */
        void requestLabelWriteValue(String labelID, String confirmPwd);
    }

    /**
     * 发送数据请求
     * @param labelID 标签ID
     * @param confirmPwd 确认密码
     */
    void doLabelWriteValue(String labelID, String confirmPwd);


    /**
     * 完成请求返回数据
     */
    void callBackLabelWriteValue(ResultBean<String, String> result);
}
