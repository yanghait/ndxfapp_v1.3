package com.ynzhxf.nd.firecontrolapp.model.inspection;

import com.ynzhxf.nd.firecontrolapp.presenter.inspection.ITaskInspectionHomePresenter;

public class InspectionTaskFactory {
    // 巡检任务列表
    public static ITaskInspectionHomePresenter.ITaskInspectionHomeListModel getTaskInspectionHome(ITaskInspectionHomePresenter presenter) {
        return new InspectionTaskHomeModel(presenter);
    }
}
