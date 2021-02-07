package com.ynzhxf.nd.firecontrolapp.base;


import android.os.Bundle;

import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.android.tu.loadingdialog.LoadingDialog;
import com.jaeger.library.StatusBarUtil;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.ynzhxf.nd.firecontrolapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：fuchangle on 2018/1/27 21:50
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    public static String CACHEPATH;


    protected Unbinder unbinder;

    public PreferencesService preferences = new PreferencesService();

    public Map<String, String> languageMap = new HashMap<>();
    /**
     * 回调函数
     */
    public LifeCycleListener lifeCycleListener;

    public void setOnLifeCycleListener(LifeCycleListener listener) {
        lifeCycleListener = listener;
    }

    public LoadingDialog.Builder builder;

    public LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getManager().push(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        CACHEPATH = getApplication().getExternalFilesDir(null) + "/fileCache";
        setContentView(getContentViewId());
        setStatusBar();
//        Slidr.attach(this);
//        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this);
        initDialog();
    }

    private void initDialog() {
        builder = new LoadingDialog.Builder(this);
        builder.setCancelable(false);
        builder.setCancelOutside(false);
        loadingDialog = builder.create();
    }

    /**
     * 获取界面布局
     *
     * @return
     */
    protected abstract int getContentViewId();


    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (lifeCycleListener != null) {
            lifeCycleListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lifeCycleListener != null) {
            lifeCycleListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lifeCycleListener != null) {
            lifeCycleListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (lifeCycleListener != null) {
            lifeCycleListener.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (lifeCycleListener != null) {
            lifeCycleListener.onDestroy();
        }
        //移除view绑定
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
        ActivityStackManager.getManager().remove(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
