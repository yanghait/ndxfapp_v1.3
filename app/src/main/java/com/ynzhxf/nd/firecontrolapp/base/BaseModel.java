package com.ynzhxf.nd.firecontrolapp.base;

public class BaseModel<T> {
    protected T mActivity;

    public BaseModel(T activity) {
        mActivity = activity;
    }

    /**
     * 获取
     *
     * @return
     */
    public T getActivity() {
        if (mActivity == null) {
            return null;
        }
        return mActivity;
    }

}
