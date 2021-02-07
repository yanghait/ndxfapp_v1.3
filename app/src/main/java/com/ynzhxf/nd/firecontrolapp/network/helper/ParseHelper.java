package com.ynzhxf.nd.firecontrolapp.network.helper;

import com.google.gson.JsonElement;

/**
 * 数据解析helper
 *
 * Created by Administrator on 2018/1/29.
 */
public interface ParseHelper {
    Object[] parse(JsonElement jsonElement);
}
