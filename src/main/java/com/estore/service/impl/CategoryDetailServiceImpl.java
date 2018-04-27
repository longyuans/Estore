package com.estore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.estore.bean.CategoryDetail;
import com.estore.dao.CategoryDetailMapper;
import com.estore.service.CategoryDetailService;
import com.estore.utils.EstoreException;
import com.estore.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class CategoryDetailServiceImpl implements CategoryDetailService{

    @Autowired
    private CategoryDetailMapper categoryDetailMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public CategoryDetail queryCategoryDetailById(Integer id) throws EstoreException {

        if(StringUtils.isEmpty(RedisUtils.get("CategoryDetail"))){
            CategoryDetail categoryDetail = categoryDetailMapper.selectByPrimaryKey(id);
            jedisCluster.set("CategoryDetail", JSONObject.toJSONString(categoryDetail));
            return categoryDetail;
        }else {
            return (CategoryDetail) JSONObject.parse(jedisCluster.get("CategoryDetail"));
        }

    }
}
