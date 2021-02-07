package com.ynzhxf.nd.firecontrolapp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.android.tu.loadingdialog.LoadingDialog;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;
import com.ynzhxf.nd.firecontrolapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类Activity
 * 备注:所有的Activity都继承自此Activity
 * 1.规范团队开发
 * 2.统一处理Activity所需配置,初始化
 * Created by Administrator on 2018/1/29.
 */
public abstract class BaseFragmentActivity extends RxFragmentActivity {

    protected Context mContext;

    public static String CACHEPATH;

    public PreferencesService preferences = new PreferencesService();

    public Map<String, String> languageMap = new HashMap<>();
    /**
     * 回调函数
     */
    public LifeCycleListener mListener;

    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }

    protected Unbinder unbinder;

    public LoadingDialog.Builder builder;

    public LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getManager().push(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        CACHEPATH = getApplication().getExternalFilesDir(null) + "/fileCache";
        setContentView(getContentViewId());
        setStatusBar();
//        Slidr.attach(this);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initDialog();
    }

    private void initDialog() {
        builder = new LoadingDialog.Builder(this);
        builder.setCancelable(false);
        builder.setCancelOutside(false);
        loadingDialog = builder.create();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
        ActivityStackManager.getManager().remove(this);
    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract int getContentViewId();

}