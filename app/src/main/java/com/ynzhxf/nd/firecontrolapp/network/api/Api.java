package com.ynzhxf.nd.firecontrolapp.network.api;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.network.retrofit.HttpResponse;
import com.ynzhxf.nd.firecontrolapp.pars.URLConstant;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    /**
     * 获取服务器的APP版本号
     *
     * @return
     */
    @POST("/NoAuthorApp/CheckAppVersion")
    Observable<HashMap<String,String>> getAppCheckVersion();


    /**
     * 获取平台的登陆令牌
     *
     * @return
     */
    @POST("/NoAuthorApp/AppLoginIdentifyKey")
    Observable<HttpResponse> loginKeyGet();

    /**
     * 登陆
     *
     * @param DeviceUUID     设备ID
     * @param DevicePlatform 设备类型
     * @param UserName       用户名
     * @param UserPwd        用户密码
     * @return
     */
    @POST(URLConstant.URL_LOGIN)
    Observable<HttpResponse> login(@Query("DeviceUUID") String DeviceUUID, @Query("DevicePlatform") String DevicePlatform, @Query("UserName") String UserName, @Query("UserPwd") String UserPwd, @Query("Key") String key, @Query("Code") String Code);

    /**
     * 检测Token是否有效
     *
     * @param DeviceUUID     设备ID
     * @param DevicePlatform 设备类型
     * @param UserName       用户名
     * @param token          令牌
     * @return
     */
    @POST(URLConstant.URL_CHECK_TOKEN)
    Observable<ResultBean<LoginInfoBean, Map<String, String>>> checkLogin(@Query("DeviceUUID") String DeviceUUID, @Query("DevicePlatform") String DevicePlatform, @Query("UserName") String UserName, @Query("Token") String token);

}
