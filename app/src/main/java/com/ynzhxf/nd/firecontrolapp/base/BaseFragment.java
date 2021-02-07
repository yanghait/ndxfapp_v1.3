package com.ynzhxf.nd.firecontrolapp.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.tu.loadingdialog.LoadingDialog;

import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：fuchangle on 2018/1/29 11:38
 */

public abstract class BaseFragment extends RxFragment {

    public abstract int getContentViewId();

    protected Unbinder unBinder;
    protected Context context;
    protected View mRootView;

    public Map<String,String> languageMap = new HashMap<>();

    /**
     * 回调函数
     */
    public LifeCycleListener mListener;

    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }

    public LoadingDialog.Builder builder;

    public LoadingDialog loadingDialog;
    public PreferencesService preferences = new PreferencesService();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        unBinder = ButterKnife.bind(this, mRootView);//绑定framgent
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        initDialog();
        initAllMembersView(savedInstanceState);
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().unregister(this);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initDialog() {
        builder = new LoadingDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setCancelOutside(false);
        loadingDialog = builder.create();
    }

    protected abstract void initAllMembersView(Bundle savedInstanceState);


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

}
