package com.ynzhxf.nd.firecontrolapp.presenter.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.VideoChannelBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

import java.util.List;

/**
 *
 * 项目视频列表获取
 * Created by nd on 2018-07-21.
 */

public interface IProjectVideoPersenter extends IBasePersenter {
    String TAG = "ProjectVideo";

    interface IProjectVideoView extends IBaseView {
        /**
         * 回调视频列表处理
         * @param result
         */
        void callBackProjectVideo(ResultBean<List<VideoChannelBean>,String> result);

    }

    interface IProjectVideoModel{
        /**
         * 发送视频列表获取请求
         * @param projectID 项目ID
         */
        void requestProjectVideo(String projectID);
    }

    /**
     * 登陆检测处理
     * @param projectID 项目ID
     */
    void doProjectVideo(String projectID);

    /**
     * 登陆检测成功回调
     * @param result
     */
    void callBackProjectVideo(ResultBean<List<VideoChannelBean>,String> result);
}
