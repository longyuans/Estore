package com.estore.model;

public class BaseResponse {
    //成功标准，默认true
    Boolean success = true;
    //是否需要重试，默认false
    Boolean isNeedRetry = false;
    //错误码
    String errorCode;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getNeedRetry() {
        return isNeedRetry;
    }

    public void setNeedRetry(Boolean needRetry) {
        isNeedRetry = needRetry;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
