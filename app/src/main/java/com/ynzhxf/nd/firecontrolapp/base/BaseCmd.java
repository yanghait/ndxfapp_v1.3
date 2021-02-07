package com.ynzhxf.nd.firecontrolapp.base;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/2/28.
 */

public abstract class BaseCmd {

   public Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
       @Override
       public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
           if(src == src.longValue()){
               return new JsonPrimitive(src.longValue());
           }
           return new JsonPrimitive(src);
       }
   }).create();

    /**
     * 获取基础request
     */
    public Map<String, Object> getBaseMap() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    /**
     * Post请求表单
     *
     * @return
     */
    public RequestBody getRequestBody() {
        Log.e("POST请求参数", gson.toJson(getRequestMap()));
        return RequestBody.create(okhttp3.MediaType.parse("application/json"), gson.toJson(getRequestMap()));

    }


    /**
     * GET请求表单
     *
     * @return
     */
    public Map getRequestQueryMap() {
        Log.e("GET请求参数", gson.toJson(getRequestMap()));
        return getRequestMap();
    }

    public abstract Map getRequestMap();
}
