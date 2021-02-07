package com.ynzhxf.nd.firecontrolapp.login;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.blankj.utilcode.util.SPUtils;
import com.jaeger.library.StatusBarUtil;
import com.ynzhxf.nd.firecontrolapp.R;
import com.ynzhxf.nd.firecontrolapp.base.BaseActivity;
import com.ynzhxf.nd.firecontrolapp.login.bean.CheckVersionBean;
import com.ynzhxf.nd.firecontrolapp.login.bean.LoginResponeBean;
import com.ynzhxf.nd.firecontrolapp.login.contract.ISplashContract;
import com.ynzhxf.nd.firecontrolapp.login.presenter.SplashPresenter;
import com.ynzhxf.nd.firecontrolapp.pars.URLConstant;
import com.ynzhxf.nd.firecontrolapp.util.PermissionsUtil;
import com.ynzhxf.nd.firecontrolapp.view.ActivityController;
import com.ynzhxf.nd.firecontrolapp.view.HelperView;
import com.ynzhxf.nd.firecontrolapp.view.compentent.CompetentMainActivity;
import com.ynzhxf.nd.firecontrolapp.view.enterprise.EnterpriseMainActivity;
import com.ynzhxf.nd.firecontrolapp.view.message.AlarmMessageListActivity;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends BaseActivity implements ISplashContract.ISplashView {

    SplashPresenter splashPresenter;

    public static PermissionsUtil.IGrantCallBack callBack;

    //推送警报信息
    private boolean isFromPush = false;
    private String typeId;
    private String relationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFromPush = getIntent().getBooleanExtra("isFromClickPush", false);
        if (isFromPush) {
            typeId = getIntent().getStringExtra("TypeId");
            relationId = getIntent().getStringExtra("MessageType");
        }
        splashPresenter = new SplashPresenter(this, this);
        splashPresenter.checkVersion();

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash2;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }

    @Override
    public void toLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void checkVersionSuccess(CheckVersionBean checkVersionBean) {
        boolean needUpdate = false;
        try {
            final String version = checkVersionBean.getVersion();
            final String downPath = checkVersionBean.getApkPath();
            PackageInfo packageInfo = null;

            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            int localVersion = packageInfo.versionCode;
            String[] s = version.split("\\.");
            int queryTotal = Integer.parseInt(s[0]) * 100 + Integer.parseInt(s[1]) * 10 + Integer.parseInt(s[2]);
            if (queryTotal > localVersion) {
                needUpdate = true;
                //此处提示更新
                String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

                if (EasyPermissions.hasPermissions(SplashActivity.this, permissions)) {
                    uploadNewversion(downPath, version);
                } else {
                    requestPermissionsCallBack(SplashActivity.this, "发现新版本,更新需要您授予读写权限!", 77, permissions, new PermissionsUtil.IGrantCallBack() {
                        @Override
                        public void result(boolean Success, int requestCode) {
                            if (Success && requestCode == 77) {
                                uploadNewversion(downPath, version);
                            }
                        }
                    });
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (!needUpdate) {
            checkLoginState();
        }
    }

    @Override
    public void chekLoginStateSuccess(LoginResponeBean loginResponeBean) {
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
            intent = new Intent(SplashActivity.this, AlarmMessageListActivity.class);
            intent.putExtra("isFromClickPush", true);
            intent.putExtra("TypeId", typeId);
            intent.putExtra("MessageType", relationId);
        }
        startActivity(intent);
        finish();
    }

    /**
     * 下载新版本
     */
    private void uploadNewversion(String downPath, String version) {
        final String queryPath = downPath;
        final String queryVersion = version;
        AllenVersionChecker
                .getInstance()
                .downloadOnly(UIData.create().setDownloadUrl(URLConstant.URL_BASE1 + downPath).setTitle(getResources().getString(R.string.app_name)).setContent("检测到新版本" + version + "，是否需要更新?"))
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {//取消下载按钮
                        checkLoginState();
                    }
                })
                .setApkDownloadListener(new APKDownloadListener() {
                    @Override
                    public void onDownloading(int progress) {

                    }

                    @Override
                    public void onDownloadSuccess(File file) {
                        ActivityController.finishAll();
                    }

                    @Override
                    public void onDownloadFail() {
                        HelperView.Toast(SplashActivity.this, "下载新版本失败！");
                        new AlertDialog.Builder(SplashActivity.this)
                                .setTitle("操作提示")
                                .setMessage("下载新版本失败,检查网络设置！您是否要重新下载？")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        checkLoginState();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        uploadNewversion(queryPath, queryVersion);
                                    }
                                }).create().show();
                    }
                }).executeMission(this);
    }

    public static void requestPermissionsCallBack(Activity activity, String title, int requestCode, String[] perm, PermissionsUtil.IGrantCallBack callBack1) {
        callBack = callBack1;
        EasyPermissions.requestPermissions(activity, title, requestCode, perm);
    }

    /**
     * 登陆状态检测
     */
    public void checkLoginState() {
        splashPresenter.checkLoginState();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void parsingDataError() {

    }
}