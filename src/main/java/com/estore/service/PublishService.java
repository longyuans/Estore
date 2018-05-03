package com.estore.service;

import com.estore.bean.Publish;

public interface PublishService {

    /**
     * 根据名字查询出版社
     * @param name 名字
     * @return Publish
     */
    Publish queryPublishByName(String name);
}
