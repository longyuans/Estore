package com.estore.dao;

import com.estore.bean.Idempotency;
import com.estore.bean.IdempotencyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface IdempotencyMapper {
    long countByExample(IdempotencyExample example);

    int deleteByExample(IdempotencyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Idempotency record);

    int insertSelective(Idempotency record);

    List<Idempotency> selectByExample(IdempotencyExample example);

    Idempotency selectByIdem(@Param("idemKey") String idemKey);

    int updateByExampleSelective(@Param("record") Idempotency record, @Param("example") IdempotencyExample example);

    int updateByExample(@Param("record") Idempotency record, @Param("example") IdempotencyExample example);

    int updateByPrimaryKeySelective(Idempotency record);

    int updateByPrimaryKey(Idempotency record);
}