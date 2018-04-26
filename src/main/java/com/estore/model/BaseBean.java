package com.estore.model;

public class BaseBean {
    //用于处理幂等
    String sourceId;
    //操作
    String operate;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}
