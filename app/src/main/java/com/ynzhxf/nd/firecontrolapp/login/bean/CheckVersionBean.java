package com.ynzhxf.nd.firecontrolapp.login.bean;

import com.google.gson.annotations.SerializedName;

public class CheckVersionBean {
    @SerializedName("apkPath")
    String apkPath = "";
    @SerializedName("version")
    String version = "";

    public String getApkPath() {
        return apkPath;
    }

    public String getVersion() {
        return version;
    }
}
