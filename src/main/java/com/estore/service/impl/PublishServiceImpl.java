package com.estore.service.impl;

import com.estore.bean.Publish;
import com.estore.dao.PublishMapper;
import com.estore.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PublishMapper publishMapper;

    @Override
    public Publish queryPublishByName(String name) {
        return publishMapper.selectByName(name);
    }
}
