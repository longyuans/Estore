package com.estore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.estore.bean.CategoryDetail;
import com.estore.bean.Product;
import com.estore.dao.CategoryDetailMapper;
import com.estore.dao.ProductMapper;
import com.estore.redis.RedisUtils;
import com.estore.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryDetailMapper categoryDetailMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public Set<Product> queryProductByFlag(String number) {
        return productMapper.selectByFlag(number);
    }

    @Override
    public Product queryProductById(Integer proId) {
        if(StringUtils.isEmpty(jedisCluster.get("queryProductById"))){
            Product product = productMapper.selectByPrimaryKey(proId);
            jedisCluster.set("queryProductByFlag", JSONObject.toJSONString(product));
            return product;
        }else {
            return (Product) JSONObject.parse(jedisCluster.get("queryProductById"));
        }
    }

    @Override
    public List<Product> queryProductByCategoryId(Integer catId) {
        if(StringUtils.isEmpty(jedisCluster.get("queryProductByCategoryId"))){
            //根据catId查询List<categoryDetail>
            List<CategoryDetail> categoryDetailList = categoryDetailMapper.selectByCatId(catId);
            List<Integer> catDetailIdList = new ArrayList<>();
            categoryDetailList.parallelStream().forEach(categoryDetail -> {
                catDetailIdList.add(categoryDetail.getId());
            });

            //根据List<catDetailId> 查询set<product>
            List<Product> productList = productMapper.selectByCatDetailIdList(catDetailIdList);
            jedisCluster.set("queryProductByFlag", JSONObject.toJSONString(productList));
            return productList;
        }else {
            return (List<Product>) JSONObject.parse(jedisCluster.get("queryProductByCategoryId"));
        }
    }

    @Override
    public Set<Product> queryProductByCateDetailId(Integer catDetailId) {
        return productMapper.selectByCatDetailId(catDetailId);
    }

    @Override
    public Set<Product> queryProductByName(String name) {
        return productMapper.selectByName(name);
    }

    @Override
    public Set<Product> queryProductByPriceRank(BigDecimal minPrice, BigDecimal maxPrice) {
        return productMapper.selectByPriceRank(minPrice, maxPrice);
    }

    @Override
    public Set<Product> queryProductByPublishId(Integer publishId) {
        return productMapper.selectByPublishId(publishId);
    }

    @Override
    public Set<Product> queryProductOrderBySale(Boolean isDesc, Boolean orOrderByPrice) {
        if (orOrderByPrice) {
            //只按价格升序排序
            return productMapper.selectByPrice();
        } else {
            //只按销售量降序排序
            return productMapper.selectBySaleNum();
        }
    }
}
