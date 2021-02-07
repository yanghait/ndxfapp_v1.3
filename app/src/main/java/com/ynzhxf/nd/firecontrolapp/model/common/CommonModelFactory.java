package com.ynzhxf.nd.firecontrolapp.model.common;

import com.ynzhxf.nd.firecontrolapp.presenter.common.INewsListPersenter;
import com.ynzhxf.nd.firecontrolapp.presenter.common.INewsURLPersenter;

/**
 *
 * Created by nd on 2018-07-28.
 */

public class CommonModelFactory {

    /**
     * 获取新闻列表
     * @param persenter
     * @return
     */
    public static INewsListPersenter.INewsListModel getNewsListModel(INewsListPersenter persenter){
        return new NewsListModel(persenter);
    }

    /**
     * 对新闻阅读量加1，并获取新闻远程访问地址
     * @param persenter
     * @return
     */
    public static INewsURLPersenter.INewsURLModel getNewsURLModel(INewsURLPersenter persenter){
        return new NewsURLModel(persenter);
    }
}
