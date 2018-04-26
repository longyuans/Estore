package com.estore.service;

import com.estore.bean.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductService {
    /**
     * 根据产品标识查询
     * @param number 标志号
     * @return Set<Product> set是为了防止数据库中product有重复的数据，而我无需展示重复数据
     */
    Set<Product> queryProductByFlag(String number);

    /**
     * 根据产品号查询产品
     * @param proId 产品号
     * @return product
     */
    Product queryProductById(Integer proId);

    /**
     * 根据大分类id查询产品
     * @param catId 大分类id
     * @return list<Product>
     */
    List<Product> queryProductByCategoryId(Integer catId);

    /**
     * 根据详细分类查询产品
     * @param catDetailId 详细分类id
     * @return set<Product>
     */
    Set<Product> queryProductByCateDetailId(Integer catDetailId);

    /**
     * 根据书的名字模糊查询
     * 索引失效导致查询效率降低
     * @param name 书名
     * @return set<Product>
     */
    Set<Product> queryProductByName(String name);

    /**
     * 根据书籍价格的范围查询
     * @param minPrice 价格下限
     * @param maxPrice 价格上限
     * @return set<Product>
     */
    Set<Product> queryProductByPriceRank(BigDecimal minPrice,BigDecimal maxPrice);

    /**
     * 根据出版社id查询书籍
     * @param publishId 出版社id
     * @return set<Product>
     */
    Set<Product> queryProductByPublishId(Integer publishId);

    /**
     * 根据销售量或价格排序，默认递减
     * @param isDesc 是否递减
     * @param orOrderByPrice 根据只价格排序 ，true 为是
     * @return Set<Product>
     */
    Set<Product> queryProductOrderBySale(Boolean isDesc,Boolean orOrderByPrice);

}
