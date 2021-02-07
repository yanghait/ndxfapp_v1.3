package com.ynzhxf.nd.firecontrolapp.login;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.ynzhxf.nd.firecontrolapp.R;
import com.ynzhxf.nd.firecontrolapp.base.BaseActivity;
import com.ynzhxf.nd.firecontrolapp.bean.platform.LoginInfoBean;
import com.ynzhxf.nd.firecontrolapp.login.bean.LoginResponeBean;
import com.ynzhxf.nd.firecontrolapp.login.contract.ILoginContract;
import com.ynzhxf.nd.firecontrolapp.login.presenter.LoginPresenter;
import com.ynzhxf.nd.firecontrolapp.pars.URLConstant;
import com.ynzhxf.nd.firecontrolapp.util.DeviceIdUtil;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;
import com.ynzhxf.nd.firecontrolapp.view.compentent.CompetentMainActivity;
import com.ynzhxf.nd.firecontrolapp.view.enterprise.EnterpriseMainActivity;
import com.ynzhxf.nd.firecontrolapp.view.message.AlarmMessageListActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {

    LoginPresenter loginPresenter;

    @BindView(R.id.login_name_edit)
    EditText login_name_edit;
    @BindView(R.id.login_pwd_edit)
    EditText login_pwd_edit;
    @BindView(R.id.code_edit)
    EditText code_edit;
    @BindView(R.id.im_code_img)
    ImageView im_code_img;
    @BindView(R.id.login_error_txt)
    TextView login_error_txt;
    @BindView(R.id.login_btn)
    Button login_btn;

    //震动提示
    private Vibrator vibrator;

    //登陆令牌
    private String key;

    //推送警报信息
    private boolean isFromPush = false;
    private String typeId;
    private String relationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        isFromPush = getIntent().getBooleanExtra("isFromClickPush", false);
        if (isFromPush) {
            typeId = getIntent().getStringExtra("TypeId");
            relationId = getIntent().getStringExtra("MessageType");
        }
        loginPresenter = new LoginPresenter(this, this);
        loginPresenter.getVerificationCode();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login2;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                        String deviceId = tm.getDeviceId();
                        executeLogin(deviceId);
                    }
                } else {
                    Toast.makeText(this, "您未完成设备需要的授权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @OnClick({R.id.login_btn,R.id.im_code_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                // 使用权限请求框架
                AndPermission.with(LoginActivity.this)
                        .runtime()
                        .permission(Permission.READ_PHONE_STATE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                                String deviceId = null;
                                if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED && tm != null) {
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
                break;
            case R.id.im_code_img:
                loginPresenter.getVerificationCode();
                break;
        }
    }

    //请求获取用户授权并获取设备卫衣标识
    private void executeLogin(String deviceID) {
        //校验登陆信息是否正确
        if (checkInputUserInfoIsRight()) {
            // showProgressDig(false);
            String queryName = login_name_edit.getText().toString();
            String queryPwd = login_pwd_edit.getText().toString();
            String queryCode = code_edit.getText().toString();
            LoginInfoBean n = new LoginInfoBean();
            n.setUserName(queryName);
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
            n.setDeviceUUID(deviceID);
            n.setDevicePlatform("android");
            n.setUserPwd(queryPwd);
            n.setKey(key);
            n.setCode(queryCode);
            loginPresenter.login(n);
        }
    }

    /**
     * 校验用户输入登陆信息
     *
     * @return
     */
    private boolean checkInputUserInfoIsRight() {
        boolean result = false;
        try {
            String queryName = login_name_edit.getText().toString();
            if (HelperTool.stringIsEmptyOrNull(queryName) || queryName.length() < 6) {
                login_name_edit.setFocusable(true);
                throw new Exception("请输入正确的用户名！");
            }
            String queryPwd = login_pwd_edit.getText().toString();
            if (HelperTool.stringIsEmptyOrNull(queryPwd) || queryPwd.length() < 6) {
                login_pwd_edit.setFocusable(true);
                throw new Exception("请输入正确的密码！");
            }
            String queryCode = code_edit.getText().toString();
            if (HelperTool.stringIsEmptyOrNull(queryCode) && queryCode.length() != 4) {
                code_edit.setFocusable(true);
                throw new Exception("请输入验证码！");
            }
            login_error_txt.setVisibility(View.INVISIBLE);
            result = true;

        } catch (Exception e) {
            result = false;
            login_error_txt.setText(e.getMessage());
            login_error_txt.setVisibility(View.VISIBLE);
            vibrator.vibrate(50);
        }
        return result;
    }

    @Override
    public void getVerificationCodeSuccess(String identifyKey) {
        loadingDialog.dismiss();
        key = identifyKey;
        Picasso.get().load(URLConstant.URL_BASE + URLConstant.URL_LOGIN_CODE + "?key=" + identifyKey).into(im_code_img);
    }

    @Override
    public void loginSuccess(LoginResponeBean loginResponeBean) {
        Intent intent = null;
        switch (loginResponeBean.getUserOrganizationType()){
            case "1":
            case "2"://主管部门
                SPUtils.getInstance().put("LoginType", 2);
                intent = new Intent(this, CompetentMainActivity.class);
                break;
            case "3"://业主
                SPUtils.getInstance().put("LoginType", 3);
                intent = new Intent(this, EnterpriseMainActivity.class);
                break;
            case "4"://维保公司
                SPUtils.getInstance().put("LoginType", 4);
                intent = new Intent(this, EnterpriseMainActivity.class);
                break;
            default:
                finish();
        }
        Set<String> queryset = new HashSet<String>();
        queryset.add(loginResponeBean.getUserName());
        JPushInterface.addTags(this, 1, queryset);
        if (isFromPush) {
            intent = new Intent(this, AlarmMessageListActivity.class);
            intent.putExtra("TypeId", typeId);
            intent.putExtra("MessageType", relationId);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoading() {
        login_error_txt.setText("msg");
        login_error_txt.setVisibility(View.INVISIBLE);
        loadingDialog.show();
    }

    @Override
    public void showToast(String msg) {
        login_error_txt.setText(msg);
        login_error_txt.setVisibility(View.VISIBLE);
        loadingDialog.dismiss();
    }

    @Override
    public void parsingDataError() {
        loadingDialog.dismiss();
    }
}