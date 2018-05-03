package com.estore.service.impl;

import com.estore.bean.Categories;
import com.estore.bean.CategoryDetail;
import com.estore.dao.CategoriesMapper;
import com.estore.dao.CategoryDetailMapper;
import com.estore.model.CategoryModel;
import com.estore.service.CategoryService;
import com.estore.utils.EstoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDetailMapper categoryDetailMapper;
    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public List<CategoryModel> queryAllCategory() throws EstoreException {

       // if (StringUtils.isEmpty(RedisUtils.get("CategoryModelList"))) {
            //query categories
            List<Categories> categoriesList = categoriesMapper.selectAllCategories();
            List<CategoryModel> modelList = categoryConvertor(categoriesList);
            //query categoryDetail
            List<CategoryDetail> detailList = categoryDetailMapper.selectAllCategoryDetail();

            //添加categoryDetail到modelList
            modelList.parallelStream().forEachOrdered(categoryModel -> {
                List<CategoryDetail> perEqualsList = detailList.parallelStream()
                        .filter(categoryDetail -> categoryDetail.getCategoryId() == categoryModel.getId())
                        .collect(Collectors.toList());
                categoryModel.setCategoryDetailList(perEqualsList);
            });
/*            if (CollectionUtils.isNotEmpty(modelList)){
                RedisUtils.set("CategoryModelList",JSONObject.toJSONString(modelList));
            }*/
            return modelList;
/*        } else {
            return (List<CategoryModel>) JSONObject.parse("CategoryModelList");
        }*/
    }

    @Override
    public Categories queryCatById(Integer id) {
        return categoriesMapper.selectByPrimaryKey(id);
    }

    private List<CategoryModel> categoryConvertor(List<Categories> categoriesList) {
        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoriesList.parallelStream().forEachOrdered(categories -> {
            CategoryModel model = new CategoryModel();
            model.setId(categories.getId());
            model.setName(categories.getName());
            categoryModelList.add(model);
        });
        return categoryModelList;
    }
}
