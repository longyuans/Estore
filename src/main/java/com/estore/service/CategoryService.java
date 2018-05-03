package com.estore.service;

import com.estore.bean.Categories;
import com.estore.model.CategoryModel;
import com.estore.utils.EstoreException;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Locale;

public interface CategoryService {
    /**
     * 查询所有的类别，大类别下包含小类别
     * @return list
     */
    List<CategoryModel> queryAllCategory() throws EstoreException;

    /**
     * 根据主键查询cat
     * @param id 主键
     * @return cat
     */
    Categories queryCatById(Integer id);
}
