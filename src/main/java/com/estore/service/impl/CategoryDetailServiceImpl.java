package com.estore.service.impl;

import com.estore.bean.CategoryDetail;
import com.estore.dao.CategoryDetailMapper;
import com.estore.service.CategoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryDetailServiceImpl implements CategoryDetailService{

    @Autowired
    private CategoryDetailMapper categoryDetailMapper;

    @Override
    public CategoryDetail queryCategoryDetailById(Integer id) {
        return categoryDetailMapper.selectByPrimaryKey(id);
    }
}
