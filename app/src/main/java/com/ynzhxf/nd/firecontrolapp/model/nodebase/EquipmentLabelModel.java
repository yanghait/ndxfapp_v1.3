package com.ynzhxf.nd.firecontrolapp.model.nodebase;

import com.ynzhxf.nd.firecontrolapp.bean.ResultBean;
import com.ynzhxf.nd.firecontrolapp.bean.TreeGridBean;
import com.ynzhxf.nd.firecontrolapp.bean.nodebase.LabelNodeBean;
import com.ynzhxf.nd.firecontrolapp.model.BaseModel;
import com.ynzhxf.nd.firecontrolapp.network.HttpUtils;
import com.ynzhxf.nd.firecontrolapp.presenter.nodebase.IEquipmentLabelPersenter;
import com.ynzhxf.nd.firecontrolapp.util.HelperTool;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 系统下的普通设备和标签数据获取
 * Created by nd on 2018-07-21.
 */

class EquipmentLabelModel extends BaseModel implements IEquipmentLabelPersenter.IEquipmentLabelModel {

    private IEquipmentLabelPersenter persenter;

    public EquipmentLabelModel(IEquipmentLabelPersenter persenter){
        this.persenter = persenter;
    }

    @Override
    public void requestEquipmentLabel(String proSysID) {
        HttpUtils.getRetrofit().getAppGetEquipmentAndLabel(HelperTool.getToken(),proSysID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean<List<TreeGridBean<LabelNodeBean>>,String>>() {
                               @Override
                               public void onNext(ResultBean<List<TreeGridBean<LabelNodeBean>>,String> resultBean) {
                                   persenter.callBackProjectSettingRepaireOrToken(resultBean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   persenter.callBackError(createResult(e),IEquipmentLabelPersenter.TAG);
                               }
                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
