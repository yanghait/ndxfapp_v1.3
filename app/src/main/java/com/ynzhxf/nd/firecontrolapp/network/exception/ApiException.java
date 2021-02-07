package com.ynzhxf.nd.firecontrolapp.network.exception;

/**
 * api接口错误/异常统一处理类
 * 异常=[程序异常,网络异常,解析异常..]
 * 错误=[接口逻辑错误eg:{code:-101,msg:账号密码错误}]
 *
 * Created by Administrator on 2018/1/29.
 */
public class ApiException extends Exception {
    private String code;//错误码
    private String msg;//错误信息
    private boolean isNotWork = true;//网络是否 true为有
//    private String desc;//错误提示

    public ApiException(Throwable throwable, String code) {
        super(throwable);
        this.code = code;
        this.msg =throwable.getMessage();
    }

    public ApiException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setNotWork(boolean notWork) {
        isNotWork = notWork;
    }

    public boolean isNotWork() {
        return isNotWork;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
}
