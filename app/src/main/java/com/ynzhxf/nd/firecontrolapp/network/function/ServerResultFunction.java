package com.ynzhxf.nd.firecontrolapp.network.function;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ynzhxf.nd.firecontrolapp.network.exception.ServerException;
import com.ynzhxf.nd.firecontrolapp.network.retrofit.HttpResponse;
import com.ynzhxf.nd.firecontrolapp.network.util.LogUtils;


import java.lang.reflect.Type;

import io.reactivex.functions.Function;


/**
 * 服务器结果处理函数
 * <p>
 * Created by Administrator on 2018/1/29.
 */
public class ServerResultFunction implements Function<HttpResponse, Object> {
    @Override
    public Object apply(HttpResponse response) throws Exception {
        //打印服务器回传结果
        LogUtils.e(response.toString());
        if (!response.isSuccess()) {
            throw new ServerException(response.getCode(), response.getErrors(), response.getMsg());
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
            @Override
            public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                if (src == src.longValue()) {
                    return new JsonPrimitive(src.longValue());
                }
                return new JsonPrimitive(src);
            }
        });
        gsonBuilder.serializeNulls(); //重点
        Gson gson = gsonBuilder.create();

        return gson.toJson(response.getResult());
    }
}
