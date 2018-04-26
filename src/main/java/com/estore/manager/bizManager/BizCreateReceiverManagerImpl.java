package com.estore.manager.bizManager;

import com.estore.bean.Receiver;
import com.estore.dao.ReceiverMapper;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.model.BaseResponse;
import com.estore.model.ReceiverResponse;
import com.estore.utils.EstoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bizCreateReceiverManager")
public class BizCreateReceiverManagerImpl extends BaseManagerImpl<Receiver,ReceiverResponse> {

    @Autowired
    private ReceiverMapper receiverMapper;

    @Override
    protected ReceiverResponse doProcess(Receiver request) throws Exception {
        ReceiverResponse response = new ReceiverResponse();
        int count = receiverMapper.insertSelective(request);
        if (1!= count){
            response.setSuccess(false);
            response.setNeedRetry(true);
            throw new EstoreException(ErrorCodeEnum.createReceiverError.toString(),"创建收件人信息失败");
        }
        response.setReceiverId(request.getId());
        return response;
    }
}
