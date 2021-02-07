package com.ynzhxf.nd.firecontrolapp.network.exception;

/**
 * 自定义服务器错误
 *
 * Created by Administrator on 2018/1/29.
 */
public class ServerException extends RuntimeException {
    private String code;
    private String msg;
    private String desc;//错误提示

    public ServerException(String code, String desc, String msg) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
