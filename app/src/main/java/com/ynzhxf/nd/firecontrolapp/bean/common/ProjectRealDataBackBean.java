package com.ynzhxf.nd.firecontrolapp.bean.common;


import com.ynzhxf.nd.firecontrolapp.bean.nodebase.LabelNodeBean;

import java.util.List;

/**
 * author hbzhou
 * date 2019/2/27 09:34
 */
public class ProjectRealDataBackBean {

    private List<LabelNodeBean> lableList;

    public List<LabelNodeBean> getLableList() {
        return lableList;
    }

    public void setLableList(List<LabelNodeBean> lableList) {
        this.lableList = lableList;
    }
}
