package com.estore.dao;

import com.estore.bean.CategoryDetail;
import com.estore.bean.CategoryDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDetailMapper {
    long countByExample(CategoryDetailExample example);

    int deleteByExample(CategoryDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryDetail record);

    int insertSelective(CategoryDetail record);

    List<CategoryDetail> selectByExample(CategoryDetailExample example);

    List<CategoryDetail> selectByCatId(Integer categoryId);

    CategoryDetail selectByPrimaryKey(Integer id);

    List<CategoryDetail> selectAllCategoryDetail();

    int updateByExampleSelective(@Param("record") CategoryDetail record, @Param("example") CategoryDetailExample example);

    int updateByExample(@Param("record") CategoryDetail record, @Param("example") CategoryDetailExample example);

    int updateByPrimaryKeySelective(CategoryDetail record);

    int updateByPrimaryKey(CategoryDetail record);
}