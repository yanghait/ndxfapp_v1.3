package com.ynzhxf.nd.firecontrolapp.network.api;

import android.content.Context;

import com.ynzhxf.nd.firecontrolapp.BuildConfig;
import com.ynzhxf.nd.firecontrolapp.network.retrofit.RetrofitUtils;


public class ApiUitls {

    private static Api api;

    //本地内测
    public static final String BASE_URL = "http://www.ynzhxf.com:8181";
    //正式/测试服务
//    public static String BaseUrl = BuildConfig.API_URL;

    public static Api getApi(Context context) {
        if (api == null) {
            api = RetrofitUtils.get(context).retrofit().create(Api.class);
        }
        return api;
    }

}
