package com.ynzhxf.nd.firecontrolapp.application;

import android.app.Application;
import android.content.Context;

class NDXFApplication extends Application {
    private static Context context;

    public static NDXFApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instances = this;
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(context);
    }

    public static Context getContext() {
        return context;
    }

    public static NDXFApplication getInstances() {
        return instances;
    }
}
