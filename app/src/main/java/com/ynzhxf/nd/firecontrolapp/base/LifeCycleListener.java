package com.ynzhxf.nd.firecontrolapp.base;

import android.os.Bundle;

/**
 * Created by Administrator on 2018/1/29.
 */

public interface LifeCycleListener {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
