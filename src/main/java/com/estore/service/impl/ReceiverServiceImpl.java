package com.estore.service.impl;

import com.estore.bean.Receiver;
import com.estore.dao.ReceiverMapper;
import com.estore.estoreEnum.OperateEnum;
import com.estore.manager.BaseBizManager;
import com.estore.model.BaseResponse;
import com.estore.model.ReceiverResponse;
import com.estore.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiverServiceImpl implements ReceiverService{

    @Autowired
    private ReceiverMapper receiverMapper;
    @Autowired
    @Qualifier("bizCreateReceiverManager")
    private BaseBizManager<Receiver,ReceiverResponse> bizCreateReceiverManager;

    @Override
    public List<Receiver> queryAllReceiverByUserId(Integer userId) {
        return receiverMapper.selectByUserId(userId);
    }

    @Override
    public ReceiverResponse insertReceiver(Receiver receiver) throws Exception {
        receiver.setSourceId((int) (Math.random() * 100000) + "");
        receiver.setOperate(OperateEnum.createReceiver.toString());
        return bizCreateReceiverManager.process(receiver);
    }

    @Override
    public BaseResponse deleteReceiver(Integer id) {
        BaseResponse response = new BaseResponse();
        //todo 欠考虑，delete出错的情况
        int count = receiverMapper.deleteByPrimaryKey(id);
        if (1!= count){
            response.setNeedRetry(true);
            response.setSuccess(false);
        }
        return response;
    }
}
