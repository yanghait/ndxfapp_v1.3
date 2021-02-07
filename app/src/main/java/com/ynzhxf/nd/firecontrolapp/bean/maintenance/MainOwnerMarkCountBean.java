package com.ynzhxf.nd.firecontrolapp.bean.maintenance;

import com.ynzhxf.nd.firecontrolapp.bean.BaseDataBean;

public class MainOwnerMarkCountBean extends BaseDataBean {

    /**
     * WorkOrderState : 20
     * Count : 9
     */

    private int WorkOrderState;
    private int Count;

    public int getWorkOrderState() {
        return WorkOrderState;
    }

    public void setWorkOrderState(int WorkOrderState) {
        this.WorkOrderState = WorkOrderState;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }
}
