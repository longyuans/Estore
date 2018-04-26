package com.estore.dao;

import com.estore.bean.Categories;
import com.estore.bean.CategoriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesMapper {
    long countByExample(CategoriesExample example);

    int deleteByExample(CategoriesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Categories record);

    int insertSelective(Categories record);

    List<Categories> selectByExample(CategoriesExample example);

    Categories selectByPrimaryKey(Integer id);

    List<Categories> selectAllCategories();

    int updateByExampleSelective(@Param("record") Categories record, @Param("example") CategoriesExample example);

    int updateByExample(@Param("record") Categories record, @Param("example") CategoriesExample example);

    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);
}