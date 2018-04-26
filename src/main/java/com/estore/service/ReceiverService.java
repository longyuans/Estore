package com.estore.service;

import com.estore.bean.Receiver;
import com.estore.model.BaseResponse;
import com.estore.model.ReceiverResponse;

import java.util.List;

public interface ReceiverService {
    /**
     * 查询当前用户所有的接收人信息
     * @param userId 用户id
     * @return  List<Receiver>
     */
    List<Receiver> queryAllReceiverByUserId(Integer userId);

    /**
     * 插入receiver
     * @param receiver receiver
     * @return BaseResponse
     */
    ReceiverResponse insertReceiver(Receiver receiver) throws Exception;

    /**
     * 删除Receiver
     * @param id receiver主键
     * @return BaseResponse
     */
    BaseResponse deleteReceiver(Integer id);
}
