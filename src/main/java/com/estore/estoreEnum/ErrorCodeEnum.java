package com.estore.estoreEnum;

public enum  ErrorCodeEnum {
    generalError("500","通用错误"),
    createUserParamError("1001","创建用户传参错误"),
    userAlreadyHaveError("1002","用户已经存在"),
    createUserError("1003","创建用户失败"),
    queryUserParamError("1004","查询用户传参错误"),
    updateUserError("1005","更新用户出错"),
    idemError("2001","幂等错误"),
    getIdemParamError("2002","幂等参数错误"),
    operateError("3001","操作类型错误"),
    orderLineCreateError("4001","订单项创建错误"),
    orderLineDelError("4004","订单项删除错误"),
    orderLineUpdateError("4005","订单项修改错误"),
    createReceiverError("5001","创建收件人信息失败"),
    createOrderError("6001","创建订单失败"),
    ;

    ErrorCodeEnum(String errorCode, String errorMessage) {
    }
}
