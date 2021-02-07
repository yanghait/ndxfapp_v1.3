package com.ynzhxf.nd.firecontrolapp.presenter.common;


import com.ynzhxf.nd.firecontrolapp.bean.PagingBean;
import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.common.NewsBean;
import com.ynzhxf.nd.firecontrolapp.presenter.IBasePersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.IBaseView;

/**
 * 使用项目ID，获取项目信息数据获取桥梁
 * Created by nd on 2018-07-16.
 */

public interface INewsListPersenter extends IBasePersenter {
    String TAG = "NewsList";

    /**
     * 视图接口
     */
    interface INewsListView extends IBaseView {
        /**
         *数据请求完成回调
         */
        void callBackNewsList(ResultBean<PagingBean<NewsBean>, String> result);

    }

    /**
     * 模型接口
     */
    interface INewsListModel{

        /**
         * 数据加载请求
         * @param pageSize 分页页码
         */
        void requestNewsList(int pageSize);
    }

    /**
     * 发送数据请求
     * @param pageSize 分页页码
     */
    void doNewsList(int pageSize);

    /**
     * 完成请求返回数据
     */
    void callBackNewsList(ResultBean<PagingBean<NewsBean>, String> result);
}
