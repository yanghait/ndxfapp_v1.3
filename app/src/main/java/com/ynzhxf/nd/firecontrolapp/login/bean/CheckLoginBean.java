package com.ynzhxf.nd.firecontrolapp.login.bean;

import com.google.gson.annotations.SerializedName;

public class CheckLoginBean {
    @SerializedName("isLogin")
    boolean isLogin = false;
    @SerializedName("loginMsg")
    String loginMsg = "";

    public boolean isLogin() {
        return isLogin;
    }

    public String getLoginMsg() {
        return loginMsg;
    }
}
