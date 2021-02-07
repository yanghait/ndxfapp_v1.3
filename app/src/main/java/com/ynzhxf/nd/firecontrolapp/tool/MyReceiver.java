package com.ynzhxf.nd.firecontrolapp.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.ynzhxf.nd.firecontrolapp.GloblePlantformDatas;
import com.ynzhxf.nd.firecontrolapp.bean.event.MessageEvent;
import com.ynzhxf.nd.firecontrolapp.util.LogUtils;
import com.ynzhxf.nd.firecontrolapp.view.ActivityController;
import com.ynzhxf.nd.firecontrolapp.view.HelperView;
import com.ynzhxf.nd.firecontrolapp.view.SplashActivity;
import com.ynzhxf.nd.firecontrolapp.view.message.AlarmMessageListActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by nd on 2018-08-04.
 */

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "JIGUANG";

    // RegistrationID 定义
    // 集成了 JPush SDK 的应用程序在第一次成功注册到 JPush 服务器时，
    // JPush 服务器会给客户端返回一个唯一的该设备的标识 - RegistrationID。
    // JPush SDK 会以广播的形式发送 RegistrationID 到应用程序。
    // 应用程序可以把此 RegistrationID 保存以自己的应用服务器上，
    // 然后就可以根据 RegistrationID 来向设备推送消息或者通知。
    public static String regId;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            Bundle bundle = intent.getExtras();
            Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
            LogUtils.showLoge(TAG, "[MyReceiver] onReceive 007007- " + intent.getAction() + ", extras: " + printBundle(bundle));

            EventBus.getDefault().post(new MessageEvent());

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

                // 对应极光后台的 - 自定义消息  默认不会出现在notification上 所以一般都选用发送通知
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 用户点击打开了通知");

                if (StringUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    HelperView.Toast(context, "未发现推送消息!");
                    return;
                }
                String notifyType = "";
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        if ("notify_type".equals(myKey)) {
                            notifyType = json.optString(myKey);
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

                /*LogUtils.showLoge("打印点击推送的消息跳转3434---", notifyType + "~~~~" + bundle.getString("notify_type") + "~~~"
                        + bundle.getString("relation_id") + "~~~" + bundle.getString("extra_key"));*/

                Intent intent2;
                if (GloblePlantformDatas.getInstance().getLoginInfoBean() == null || ActivityController.getHasCount() == 0) {
                    intent2 = new Intent(context, SplashActivity.class);
                    //intent2.putExtra("isFromClickPush", true);
                } else {
                    intent2 = new Intent(context, AlarmMessageListActivity.class);
                }
                intent2.putExtra("TypeId", notifyType);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                switch (notifyType) {
                    case "0":
                        intent2.putExtra("MessageType", "69");
                        break;
                    case "1":
                        intent2.putExtra("MessageType", "72");
                        break;
                    case "2":
                        break;
                    case "3":
                        intent2.putExtra("MessageType", "71");
                        break;
                        // 适配概览类型消息点击事件
                    case "4":
                        intent2.putExtra("MessageType", "75");
                        break;
                }
                context.startActivity(intent2);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }

        } catch (Exception e) {
            LogUtils.showLoge("007", e.getMessage());
        }
    }


    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {

        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                //sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}
