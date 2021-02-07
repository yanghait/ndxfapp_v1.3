package com.ynzhxf.nd.firecontrolapp.login.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.ynzhxf.nd.firecontrolapp.base.BasePresenter;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.login.LoginActivity;
import com.ynzhxf.nd.firecontrolapp.login.bean.LoginResponeBean;
import com.ynzhxf.nd.firecontrolapp.login.contract.ILoginContract;
import com.ynzhxf.nd.firecontrolapp.login.model.LoginModel;
import com.ynzhxf.nd.firecontrolapp.network.exception.ApiException;
import com.ynzhxf.nd.firecontrolapp.network.observer.HttpRxObserver;
import com.ynzhxf.nd.firecontrolapp.util.DeviceIdUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<ILoginContract.ILoginView, LoginActivity> implements ILoginContract.ILoginPresenter {

    LoginModel loginModel;

    public LoginPresenter(ILoginContract.ILoginView view, LoginActivity activity) {
        super(view, activity);
        loginModel = new LoginModel(activity);
    }

    @Override
    public void getVerificationCode() {
        HttpRxObserver httpRxObserver = new HttpRxObserver() {
            @Override
            protected void onStart(Disposable d) {
                getView().showLoading();
            }

            @Override
            protected void onError(ApiException e) {
                getView().showToast(e.getMsg());
            }

            @Override
            protected void onSuccess(Object response) {
                String code = gson.fromJson(response.toString(), String.class);
                getView().getVerificationCodeSuccess(code);
            }
        };
        loginModel.requestGetVerificationCode().subscribe(httpRxObserver);
    }

    public void login(LoginInfoBean loginInfoBean) {
        HttpRxObserver httpRxObserver = new HttpRxObserver() {
            @Override
            protected void onStart(Disposable d) {
                getView().showLoading();
            }

            @Override
            protected void onError(ApiException e) {
                getView().showToast(e.getMsg());
            }

            @Override
            protected void onSuccess(Object response) {
                LoginResponeBean userInfo = gson.fromJson(response.toString(), LoginResponeBean.class);
                preferences.saveUserInfo(response.toString());
                preferences.saveToken(userInfo.getToken());
                getView().loginSuccess(userInfo);
            }
        };
        loginModel.requestLogin(loginInfoBean).subscribe(httpRxObserver);
    }

    private void getDeviceId(String uName, String uPass, String code) {
        AndPermission.with(getActivity())
                .runtime()
                .permission(Permission.READ_PHONE_STATE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                        String deviceId = null;
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED && tm != null) {
                            deviceId = tm.getDeviceId();
                        }
                        executeLogin(deviceId);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        executeLogin(null);
                        //ToastUtils.showLong("读取设备信息被拒绝,应用可能使用异常!!");
                    }
                })
                .start();
    }

    private void executeLogin(String deviceID) {
        if (StringUtils.isEmpty(deviceID) || deviceID.equals("0000000")) {
            // 获取设备唯一id老方法在android 10版本已经失效
            // deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            // 使用构造方法获取设备唯一id并保存 但卸载重装后可能改变
            deviceID = SPUtils.getInstance().getString("DeviceId");
            if (StringUtils.isEmpty(deviceID)) {
                deviceID = DeviceIdUtil.deviceIdShort;
                SPUtils.getInstance().put("DeviceId", deviceID);
            }
        }

    }


}
