package com.ynzhxf.nd.firecontrolapp.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.ynzhxf.nd.firecontrolapp.application.ApplicationPlatform;


/**
 * Created by Administrator on 2018/2/24.
 */

public class PreferencesService {

    private Context mcontext;

    private String language = "languageStr";

    private String account = "account";

    private String password = "password";

    private String userinfo = "userinfo";

    private String token = "token";

    private String okToken = "oktoken";

    private String websocket = "websocket";

    private String restaurantUid = "restaurantUid";

    private String entRestaurantInfo = "entRestaurantInfo";

    private String guestRestaurantInfo = "guestRestaurantInfo";

    private String configLanguagePackage = "configLanguagePackage";

    private String dynamicLanguagePackage = "dynamicLanguagePackage";

    public PreferencesService() {
        this.mcontext = ApplicationPlatform.getContext();
    }

    /**
     * 记录语言
     */
    public void saveLanguage(String setLanguage) {
        SharedPreferences preferences = mcontext.getSharedPreferences(language, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(language, setLanguage);
        editor.commit();
    }

    /**
     * 获取语言
     *
     * @return
     */
    public String getLanguage() {
        SharedPreferences preferences = mcontext.getSharedPreferences(language, Context.MODE_PRIVATE);
        return preferences.getString(language, "");
    }

    /**
     * 记录用户token
     */
    public void saveToken(String str) {
        SharedPreferences preferences = mcontext.getSharedPreferences(token, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(token, str);
        editor.commit();
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public String getToken() {
        SharedPreferences preferences = mcontext.getSharedPreferences(token, Context.MODE_PRIVATE);
        return preferences.getString(token, "");
    }

    /**
     * 记录用户token
     */
    public void saveOKToken(String str) {
        SharedPreferences preferences = mcontext.getSharedPreferences(okToken, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(token, str);
        editor.commit();
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public String getOKToken() {
        SharedPreferences preferences = mcontext.getSharedPreferences(okToken, Context.MODE_PRIVATE);
        return preferences.getString(token, "");
    }

    /**
     * 保存用户账号
     *
     * @param name
     */
    public void saveUserAccount(String name) {
        SharedPreferences preferences = mcontext.getSharedPreferences(account, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(account, name);
        editor.commit();
    }

    /**
     * 获取用户账号
     *
     * @return
     */
    public String getUserAccount() {
        SharedPreferences preferences = mcontext.getSharedPreferences(account, Context.MODE_PRIVATE);
        return preferences.getString(account, "");
    }

    /**
     * 保存用户密码
     *
     * @param pass
     */
    public void saveUserPassword(String pass) {
        SharedPreferences preferences = mcontext.getSharedPreferences(password, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(password, pass);
        editor.commit();
    }

    /**
     * 获取用户账号
     *
     * @return
     */
    public String getUserPassword() {
        SharedPreferences preferences = mcontext.getSharedPreferences(password, Context.MODE_PRIVATE);
        return preferences.getString(password, "");
    }

    /**
     * 保存用户登录信息
     *
     * @param info
     */
    public void saveUserInfo(String info) {
        SharedPreferences preferences = mcontext.getSharedPreferences(userinfo, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(userinfo, info);
        editor.commit();
    }

    /**
     * 获取用户登录信息
     *
     * @return
     */
    public String getUserInfo() {
        SharedPreferences preferences = mcontext.getSharedPreferences(userinfo, Context.MODE_PRIVATE);
        return preferences.getString(userinfo, "");
    }


    /**
     * 记录Websocket信息
     */
    public void saveWebSocketInfo(String str) {
        SharedPreferences preferences = mcontext.getSharedPreferences(websocket, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(websocket, str);
        editor.commit();
    }

    /**
     * 获取Websocket信息
     *
     * @return
     */
    public String getWebSocketInfo() {
        SharedPreferences preferences = mcontext.getSharedPreferences(websocket, Context.MODE_PRIVATE);
        return preferences.getString(websocket, "");
    }

    /**
     * 保存ent餐厅信息
     *
     * @param restaurantJson
     */
    public void saveEntRestaurant(String restaurantJson) {
        SharedPreferences preferences = mcontext.getSharedPreferences(entRestaurantInfo, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(entRestaurantInfo, restaurantJson);
        editor.commit();
    }

    public String getEntRestaurant() {
        SharedPreferences preferences = mcontext.getSharedPreferences(entRestaurantInfo, Context.MODE_PRIVATE);
        String sei = preferences.getString(entRestaurantInfo, "");
        return sei;
    }

    /**
     * 保存guest餐厅信息
     *
     * @param restaurantJson
     */
    public void saveGuestRestaurant(String restaurantJson) {
        SharedPreferences preferences = mcontext.getSharedPreferences(guestRestaurantInfo, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(guestRestaurantInfo, restaurantJson);
        editor.commit();
    }

    public String getGuestRestaurant() {
        SharedPreferences preferences = mcontext.getSharedPreferences(guestRestaurantInfo, Context.MODE_PRIVATE);
        String sei = preferences.getString(guestRestaurantInfo, "");
        return sei;
    }

    /**
     * 儲存餐廳UID
     *
     * @param uid
     */
    public void saveRestaurantUid(String uid) {
        SharedPreferences preferences = mcontext.getSharedPreferences(restaurantUid, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(restaurantUid, uid);
        editor.commit();
    }

    public String getRestaurantUid() {
        SharedPreferences preferences = mcontext.getSharedPreferences(restaurantUid, Context.MODE_PRIVATE);
        String sei = preferences.getString(restaurantUid, "");
        return sei;
    }

    /**
     * 保存配置类信息语言包
     *
     * @param str
     */
    public void saveConfigLanguagePackage(String str) {
        SharedPreferences preferences = mcontext.getSharedPreferences(configLanguagePackage, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(configLanguagePackage, str);
        editor.commit();
    }

    public String getConfigLanguagePackage() {
        SharedPreferences preferences = mcontext.getSharedPreferences(configLanguagePackage, Context.MODE_PRIVATE);
        String sei = preferences.getString(configLanguagePackage, "");
        return sei;
    }

    /**
     * 保存动态信息语言包
     *
     * @param str
     */
    public void saveDynamicLanguagePackage(String str) {
        SharedPreferences preferences = mcontext.getSharedPreferences(dynamicLanguagePackage, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(dynamicLanguagePackage, str);
        editor.commit();

    }

    public String getDynamicLanguagePackage() {
        SharedPreferences preferences = mcontext.getSharedPreferences(dynamicLanguagePackage, Context.MODE_PRIVATE);
        String sei = preferences.getString(dynamicLanguagePackage, "");
        return sei;
    }
}
