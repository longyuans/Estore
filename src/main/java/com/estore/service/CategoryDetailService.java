package com.estore.service;

import com.estore.bean.CategoryDetail;
import com.estore.utils.EstoreException;

public interface CategoryDetailService {
    CategoryDetail queryCategoryDetailById(Integer id) throws EstoreException;
}
