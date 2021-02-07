package com.ynzhxf.nd.firecontrolapp.network.exception;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.ParseException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.HttpException;


/**
 * 错误/异常处理工具
 * <p>
 * Created by Administrator on 2018/1/29.
 */
public class ExceptionEngine {

    public static final String UN_KNOWN_ERROR = "1000";//未知错误
    public static final String ANALYTIC_SERVER_DATA_ERROR = "1001";//解析(服务器)数据错误
    public static final String ANALYTIC_CLIENT_DATA_ERROR = "1002";//解析(客户端)数据错误
    public static final String CONNECT_ERROR = "1003";//网络连接错误
    public static final String TIME_OUT_ERROR = "1004";//网络连接超时

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;


    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpExc = (HttpException) e;
            ex = new ApiException(e, httpExc.code() + "");
            if (ex.getCode().equals("500")) {
                ResponseBody responseBody = httpExc.response().errorBody();
                long contentLength = responseBody.contentLength();
                BufferedSource source = responseBody.source();
                Buffer buffer = source.buffer();
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(Charset.forName("UTF-8"));
                    } catch (Exception IOex) {
                        IOex.printStackTrace();
                    }
                }
                if (contentLength != 0) {
                    String result = buffer.clone().readString(charset);
                    Log.d(ex.getCode(), "handleException: " + result);
                }
                ex.setMsg("Failure to connect to server");
            }
//            else if (ex.getCode().equals("401")) {
//                EventBus.getDefault().post(new LoginTimeOutEventMessage());
//                ex.setMsg("Login timeout, please log in again!");
//            }
            //均视为网络错误
            ex.setNotWork(false);
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc, serverExc.getMsg());
            ex.setMsg(serverExc.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {  //解析数据错误
            ex = new ApiException(e, ANALYTIC_SERVER_DATA_ERROR);
            ex.setMsg("Parsing error");
            return ex;
        } else if (e instanceof ConnectException) {//连接网络错误
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg("connection failed");
            ex.setNotWork(false);
            return ex;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg("Network Timeout");
            ex.setNotWork(false);
            return ex;
        } else if (e instanceof UnknownHostException) {//网络超时
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg("Network Connection Failure");
            ex.setNotWork(false);
            return ex;
        } else {  //未知错误
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg("unknown error");
            return ex;
        }
    }


}
