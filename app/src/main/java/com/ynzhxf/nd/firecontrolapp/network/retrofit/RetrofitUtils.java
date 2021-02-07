package com.ynzhxf.nd.firecontrolapp.network.retrofit;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ynzhxf.nd.firecontrolapp.base.PreferencesService;
import com.ynzhxf.nd.firecontrolapp.network.api.ApiUitls;
import com.ynzhxf.nd.firecontrolapp.network.util.DoubleDefault0Adapter;
import com.ynzhxf.nd.firecontrolapp.network.util.HttpsUtils;
import com.ynzhxf.nd.firecontrolapp.network.util.IntegerDefault0Adapter;
import com.ynzhxf.nd.firecontrolapp.network.util.LogUtils;
import com.ynzhxf.nd.firecontrolapp.network.util.LongDefault0Adapter;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitUtils工具类
 */
public class RetrofitUtils {
    /**
     * 接口地址
     */
    public static final int CONNECT_TIME_OUT = 30;//连接超时时长x秒
    public static final int READ_TIME_OUT = 30;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 30;//写数据接超时时长x秒
    private static RetrofitUtils mInstance = null;

    private Gson gson;

    private RetrofitUtils(Context context) {
    }

    public static RetrofitUtils get(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置okHttp
     */
    private static OkHttpClient okHttpClient() {
        //开启Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.e("okHttp:" + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        //取得BKS密库实例
//        KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
//        InputStream is = context.getResources().openRawResource(R.raw.traint);
//        try {
//            tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
//        } finally {
//            is.close();
//        }
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        PreferencesService preferencesService = new PreferencesService();
                        Request request = original.newBuilder()
                                .method(original.method(), original.body())
                                .header("Authorization", "Bearer " + preferencesService.getToken())
                                .header("oktoken", preferencesService.getOKToken())
                                .header("CurrentSiteId", preferencesService.getRestaurantUid())
                                .build();
                        Response response = chain.proceed(request);
                        return response;
                    }
                })
//                .hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String s, SSLSession sslSession) {
//                        return true;
//                    }
//                })
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(logging)
                .build();
        return client;
    }

    /**
     * 获取Retrofit
     */
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(ApiUitls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public Retrofit retrofit(String Url) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                    .registerTypeAdapter(long.class, new LongDefault0Adapter())
                    .create();
        }
        return gson;
    }

}
