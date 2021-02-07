package com.ynzhxf.nd.firecontrolapp.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * http响应参数实体类
 * 通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 * 备注:这里与服务器约定返回格式
 */
public class HttpResponse<T> {

    /**
     * 状态码
     */
    @SerializedName("code")
    private String code;

    /**
     * 返回消息
     */
    @SerializedName("message")
    private String message = null;

    /**
     * 数据对象
     */
    @SerializedName("data")
    private T data;
    /**
     * 是否登陆
     */
    private boolean isLogin;

    /**
     * 登陆消息
     */
    private String loginMsg;

    /**
     * 额外数据
     */
    private Object extra;

    /**
     * 是否成功
     *
     * @return
     */
    private boolean success;

    private String version;

    public String toString() {
        String response = "[http response]" + "{code: " + code + ",data:" + data + ",message:" + message + ",success:"+success+",version"+version+"}";
        return response;

    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrors() {
        return code;
    }

    public String getCode() {
        return code;
    }

    public Object getResult() {
        return data;
    }

    public String getMsg() {
        return message;
    }

}
