package com.ynzhxf.nd.firecontrolapp.presenter.message;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.AlarmLogBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

/**
 * 忽略报警消息
 * Created by nd on 2018-08-02.
 */

public interface IIgnoreAlarmLogPersenter extends IBasePersenter {
    String TAG = "IgnoreAlarmLog";
    /**
     * 视图接口
     */
    interface IIgnoreAlarmLogView extends IBaseView {
        /**
         *数据请求完成回调
         */
        void callBackIgnoreAlarmLog(ResultBean<AlarmLogBean, String> result);
    }

    /**
     * 模型接口
     */
    interface IIgnoreAlarmLogModel{

        /**
         * 数据加载请求
         * @param  ID 报警记录ID
         */
        void requestIgnoreAlarmLog(String ID);
    }

    /**
     * 发送数据请求
     */
    void doIgnoreAlarmLog(String ID);

    /**
     * 完成请求返回数据
     */
    void callBackIgnoreAlarmLog(ResultBean<AlarmLogBean, String> result);
}
