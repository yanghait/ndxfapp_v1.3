package com.ynzhxf.nd.firecontrolapp.network.function;




import androidx.annotation.NonNull;

import com.ynzhxf.nd.firecontrolapp.network.exception.ExceptionEngine;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * http结果处理函数
 * <p>
 * Created by Administrator on 2018/1/29.
 */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
//        打印具体错误
//        ResponseBody body = ((HttpException) throwable).response().errorBody();
//        try {
//            System.out.println(body.string());
//        } catch (IOException IOe) {
//            IOe.printStackTrace();
//        }

        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
