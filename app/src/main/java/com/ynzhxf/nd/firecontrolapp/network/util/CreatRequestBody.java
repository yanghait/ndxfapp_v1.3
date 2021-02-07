package com.ynzhxf.nd.firecontrolapp.network.util;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/2/9.
 */

public class CreatRequestBody {
    static Gson gson = new Gson();

    static public RequestBody getRequestBody(Map map) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), gson.toJson(map));
    }
}
