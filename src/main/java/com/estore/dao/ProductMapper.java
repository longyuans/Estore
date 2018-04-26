package com.estore.dao;

import com.estore.bean.Product;
import com.estore.bean.ProductExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    Set<Product> selectByFlag(@Param("flag") String flag);

    Set<Product> selectByName(@Param("name") String name);

    Set<Product> selectByCatDetailId(@Param("categoryDetailId") Integer categoryDetailId);

    Set<Product> selectByPublishId(@Param("publishId") Integer publishId);

    Set<Product> selectByPrice();

    Set<Product> selectBySaleNum();

    Set<Product> selectByPriceRank(@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);

    List<Product> selectByCatDetailIdList(List<Integer> catDetailIdList);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}