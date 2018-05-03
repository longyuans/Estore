package com.estore.service.impl;

import com.estore.bean.User;
import com.estore.dao.UserMapper;
import com.estore.estoreEnum.DataSourceEnum;
import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.manager.BaseBizManager;
import com.estore.model.BaseResponse;
import com.estore.mysqlRouter.DataSourceHolder;
import com.estore.service.UserService;
import com.estore.utils.EstoreException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    @Qualifier("bizCreateUserManager")
    private BaseBizManager<User,BaseResponse> bizCreateUserManager;
    @Autowired
    @Qualifier("bizUpdateUserManager")
    private BaseBizManager<User,BaseResponse> bizUpdateUserManager;

    @Override
    public BaseResponse createUser(User user) throws Exception {
        DataSourceHolder.setDataSources(DataSourceEnum.ds2.getKey());
        return bizCreateUserManager.process(user);
    }

    @Override
    public BaseResponse updateUser(User user) throws Exception {
        return bizUpdateUserManager.process(user);
    }

    @Override
    public User queryUserByName(String name) throws EstoreException {
        if(StringUtils.isEmpty(name)){
            throw new EstoreException(ErrorCodeEnum.queryUserParamError.toString(),"查询用户传参错误");
        }
        User user = userMapper.selectByName(name);
        return user;
    }
}
