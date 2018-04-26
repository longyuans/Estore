package com.estore.manager.bizManager;

import com.estore.bean.User;
import com.estore.dao.UserMapper;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.model.BaseResponse;
import com.estore.utils.EstoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bizUpdateUserManager")
public class BizUpdateUserManagerImpl extends BaseManagerImpl<User,BaseResponse> {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseResponse doProcess(User user) throws Exception {
        BaseResponse response = new BaseResponse();
        if (null == user) {
            response.setErrorCode(ErrorCodeEnum.createUserParamError.toString());
            throw new EstoreException("1001", "用户更新失败,用户不能为空");
        }
        if(0 == user.getId()){
            throw new EstoreException("1001", "用户更新失败,用户不能为空");
        }
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (1 != count) {
            response.setSuccess(false);
            response.setNeedRetry(true);
            response.setErrorCode(ErrorCodeEnum.updateUserError.toString());
            throw new EstoreException("1005", "用户更新失败");
        }
        return response;
    }
}
