package com.ynzhxf.nd.firecontrolapp.login.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponeBean implements Serializable {
    @SerializedName("UserName")
    String userName = "";
    @SerializedName("UserPwd")
    String userPsw = "";
    @SerializedName("DevicePlatform")
    String devicePlatform = "";
    @SerializedName("DeviceUUID")
    String deviceUUID = "";
    @SerializedName("Token")
    String token = "";
    @SerializedName("TokenLoseTime")
    String  tokenLoseTime = "";
    @SerializedName("Key")
    String key = "";
    @SerializedName("Code")
    String code = "";
    @SerializedName("UserOrganizationType")
    String userOrganizationType = "";
    @SerializedName("ID")
    String id = "";
    @SerializedName("IsNew")
    boolean isNew = false;
    @SerializedName("loginUser")
    LoginUserBean loginUser;
    @SerializedName("userOrg")
    LoginUserOrgBean userOrg;

    public String getUserName() {
        return userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public String getToken() {
        return token;
    }

    public String getTokenLoseTime() {
        return tokenLoseTime;
    }

    public String getKey() {
        return key;
    }

    public String getCode() {
        return code;
    }

    public String getUserOrganizationType() {
        return userOrganizationType;
    }

    public String getId() {
        return id;
    }

    public boolean isNew() {
        return isNew;
    }

    public LoginUserBean getLoginUser() {
        return loginUser;
    }

    public LoginUserOrgBean getUserOrg() {
        return userOrg;
    }
}
