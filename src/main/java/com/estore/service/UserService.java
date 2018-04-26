package com.estore.service;

import com.estore.bean.User;
import com.estore.model.BaseResponse;
import com.estore.utils.EstoreException;

public interface UserService {

    /**
     * 创建用户
     * @param user  用户
     * @return BaseResponse
     */
    BaseResponse createUser(User user) throws Exception;

    /**
     * 修改用户
     * @param user 用户
     * @return BaseResponse
     */
    BaseResponse updateUser(User user) throws Exception;

    /**
     * 查询用户
     * @param name 用户
     * @return BaseResponse
     */
    User queryUserByName(String name) throws Exception;
}
